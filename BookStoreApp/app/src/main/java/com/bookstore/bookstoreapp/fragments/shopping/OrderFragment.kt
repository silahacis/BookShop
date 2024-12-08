package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.activities.RetrofitClient
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.adapters.OrderAdapter
import com.bookstore.bookstoreapp.databinding.FragmentOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAllOrders.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter with click listener
        orderAdapter = OrderAdapter(emptyList()) { order ->
            navigateToOrderDetails(order.id)
        }
        binding.rvAllOrders.adapter = orderAdapter
    }

    override fun onResume() {
        super.onResume()
        // Fetch orders whenever the fragment is resumed
        fetchOrders()
    }



    private fun fetchOrders() {
        val apiService = RetrofitClient.instance
        val customerId = 1
        apiService.getOrdersByCustomerId(customerId).enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    val orders = response.body()
                    if (!orders.isNullOrEmpty()) {
                        binding.tvEmptyOrders.visibility = View.GONE
                        orderAdapter.updateOrders(orders)
                    } else {
                        binding.tvEmptyOrders.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                println("Failed to fetch orders: ${t.message}")
            }
        })
    }

    private fun navigateToOrderDetails(orderId: Int) {
        val bundle = Bundle().apply {
            putInt("ORDER_ID", orderId)
        }

        // Use NavController to navigate to OrderDetailsFragment
        findNavController().navigate(R.id.action_orderFragment_to_billingFragment, bundle)
    }
}
