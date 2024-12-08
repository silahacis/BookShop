package com.bookstore.bookstoreapp.fragments.shopping

import CartAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.CartItem
import com.bookstore.bookstoreapp.data.Order
import com.bookstore.bookstoreapp.databinding.FragmentCartBinding
import com.bookstore.bookstoreapp.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var adapter: CartAdapter
    private lateinit var binding: FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.rvCart
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            adapter = CartAdapter(
                cartItems.toMutableList(),
                onIncreaseQuantity = { cartItem ->
                    cartViewModel.increaseQuantity(cartItem.bookID)
                    updateTotalPrice(cartItems)
                },
                onDecreaseQuantity = { cartItem ->
                    cartViewModel.decreaseQuantity(cartItem.bookID)
                    updateTotalPrice(cartItems)
                },
                onRemoveFromCart = { cartItem ->
                    cartViewModel.removeFromCart(cartItem)
                    updateTotalPrice(cartItems)
                })
            recyclerView.adapter = adapter
            updateTotalPrice(cartItems)
        }
        binding.buttonCheckout.setOnClickListener {
            val cartItems = cartViewModel.cartItems.value ?: emptyList()
            if (cartItems.isEmpty()) {
                binding.buttonCheckout.isEnabled = false
                return@setOnClickListener
            } else {
                //showOrderConfirmationDialog()
                findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
            }
        }
    }

    private fun updateTotalPrice(cartItems: List<CartItem>) {
        val totalPrice = cartItems.sumOf { it.quantity * it.price }
        binding.tvTotalPrice.text = "$totalPrice ₺"
    }

    private fun showEmptyCartDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Empty Cart")
            .setMessage("Cart is Empty")
            .setPositiveButton(getString(R.string.app_name), null)
            .show()
    }

    private fun showOrderConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Order Confirmation")
            .setMessage("Do you want to place the order?")
            .setPositiveButton("Yes") { _, _ ->
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}