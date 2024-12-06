package com.bookstore.bookstoreapp.fragments.shopping

import CartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookstore.bookstoreapp.R
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

        cartViewModel.cartItems.observe(viewLifecycleOwner){ cartItems ->
            adapter = CartAdapter(
                cartItems.toMutableList(),
                onIncreaseQuantity = {cartItem ->
                    cartViewModel.increaseQuantity(cartItem.bookID)
                },
                onDecreaseQuantity = {cartItem ->
                    cartViewModel.decreaseQuantity(cartItem.bookID)
                },
                onRemoveFromCart = {cartItem ->
                    cartViewModel.removeFromCart(cartItem)
                })
            recyclerView.adapter = adapter
        }
    }
}