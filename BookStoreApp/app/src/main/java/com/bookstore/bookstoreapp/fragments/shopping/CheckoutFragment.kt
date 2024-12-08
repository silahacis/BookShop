package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.activities.RetrofitClient
import com.bookstore.bookstoreapp.activities.models.ApiResponse
import com.bookstore.bookstoreapp.activities.models.Book
import com.bookstore.bookstoreapp.activities.models.CreateOrderRequest
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.databinding.FragmentCheckoutBinding
import com.bookstore.bookstoreapp.viewmodel.CartViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutFragment : Fragment() {

    private val cartViewModel: CartViewModel by activityViewModels()
    private lateinit var binding: FragmentCheckoutBinding
    val apiService = RetrofitClient.instance

    private var paymentMethod: String = ""
    private var billingMethod: Int = -1 // 0 for Physical, 1 for Digital

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.address.addTextChangedListener { checkButtonConditions() }

        binding.paymentMethodGroup.setOnCheckedChangeListener { _, checkedId ->
            paymentMethod = when (checkedId) {
                R.id.payByCard -> "CreditCard"
                R.id.payByPaypal -> "PayPal"
                else -> ""
            }
            checkButtonConditions()
        }

        binding.billingMethods.setOnCheckedChangeListener { _, checkedId ->
            billingMethod = when (checkedId) {
                R.id.physicalBill -> 0
                R.id.digitalBill -> 1
                else -> -1
            }
            checkButtonConditions()
        }

        binding.buttonPlaceOrder.setOnClickListener {
            createOrderRequest()  // This triggers the order creation process
        }

        checkButtonConditions()  // Ensures button is disabled initially if conditions are not met
    }

    private fun checkButtonConditions() {
        val isAddressNotEmpty = binding.address.text.toString().isNotEmpty()
        val isPaymentSelected = binding.paymentMethodGroup.checkedRadioButtonId != -1
        val isBillingMethodSelected = binding.billingMethods.checkedRadioButtonId != -1

        binding.buttonPlaceOrder.isEnabled = isAddressNotEmpty && isPaymentSelected && isBillingMethodSelected
    }

    private fun createOrderRequest() {
        val address = binding.address.text.toString()
        val customerId = 1 // Ideally, this should be fetched dynamically from the logged-in user's data

        // Retrieve the list of books from the cart
        val cartItems = cartViewModel.cartItems.value ?: emptyList()
        val books = cartItems.map {
            Book(
                id = it.bookID,
                price = it.price,
                categories = emptyList(),
                stock = 110,
                title = it.name,
                author = it.authorName // Ideally, this should be dynamically fetched
            )
        }

        val createOrderRequest = CreateOrderRequest(
            books = books,
            customerId = customerId,
            paymentMethod = paymentMethod,
            orderAddress = address,
            invoiceType = if (billingMethod == 0) 0 else 1
        )

        // Make the API call to create the order
        apiService.createOrder(createOrderRequest).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if (response.isSuccessful) {
                    val order = response.body()
                    println("Order: $order")

                    if (order != null) {
                        // Navigate to the order fragment (confirmation page)
                        findNavController().navigate(R.id.action_checkoutFragment_to_orderFragment)
                        cartViewModel.clearCart()

                        // Continue simulating the order in the background
                        simulateOrder(order.id)
                    } else {
                        Toast.makeText(requireContext(), "Order details are missing!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle unsuccessful response (e.g., validation errors from the server)
                    Toast.makeText(requireContext(), "Failed to place order: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                // Handle failure (e.g., network issues)
                Toast.makeText(requireContext(), "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun simulateOrder(orderId: Int) {
        apiService.simulateOrder(orderId).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (isAdded) {
                        Toast.makeText(requireContext(), "Order simulated: ${apiResponse?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (isAdded) {
                        Toast.makeText(requireContext(), "Failed to simulate order: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "An error occurred during simulation: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



}
