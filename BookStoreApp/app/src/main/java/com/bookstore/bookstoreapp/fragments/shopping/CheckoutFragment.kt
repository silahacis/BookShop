package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.databinding.FragmentCartBinding
import com.bookstore.bookstoreapp.databinding.FragmentCheckoutBinding
import androidx.navigation.fragment.findNavController
import com.bookstore.bookstoreapp.data.Order
import com.bookstore.bookstoreapp.viewmodel.CartViewModel
import com.bookstore.bookstoreapp.viewmodel.OrderViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


class CheckoutFragment : Fragment() {
    private val cartViewModel: CartViewModel by activityViewModels()
    private val orderViewModel: OrderViewModel by activityViewModels()

    var paymentMethod: String = ""
    var billingMethod: Int = -1

    private lateinit var binding : FragmentCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.address.addTextChangedListener {
            checkButtonConditions()
        }

        binding.paymentMethodGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.payByCard -> paymentMethod = "CreditCard"
                R.id.payByPaypal -> paymentMethod = "PayPal"
            }
            checkButtonConditions()
        }

        binding.billingMethods.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.physicalBill -> billingMethod = 0
                R.id.digitalBill -> billingMethod = 1
            }
            checkButtonConditions()
        }

        checkButtonConditions()
        binding.buttonPlaceOrder.setOnClickListener {

            findNavController().navigate(R.id.action_checkoutFragment_to_orderFragment)

        }
    }

    fun checkButtonConditions() {
        val isAddressNotEmpty = binding.address.text.toString().isNotEmpty()
        val isPaymentSelected = binding.paymentMethodGroup.checkedRadioButtonId != -1
        val isBillingMethodSelected = binding.billingMethods.checkedRadioButtonId != -1

        // Enable button only if all conditions are met
        binding.buttonPlaceOrder.isEnabled = isAddressNotEmpty && isPaymentSelected && isBillingMethodSelected
    }
}