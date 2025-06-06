package com.bookstore.bookstoreapp.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.adapters.ProductsAdapter
import com.bookstore.bookstoreapp.databinding.FragmentMysteryBinding
import com.bookstore.bookstoreapp.helper.ProductRepository

class MysteryFragment : BaseCategoryFragment() {

    private var _binding: FragmentMysteryBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter : ProductsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMysteryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Fetch horror books
        val mysteryBooks = ProductRepository.getProductsByCategory("Mystery")

        val adapter = ProductsAdapter(mysteryBooks){selectedProduct ->
            val bundle = Bundle().apply {
                putParcelable("product", selectedProduct)
            }
            findNavController().navigate(R.id.productDetailsFragment, bundle)
        }
        // Set up RecyclerView
        binding.rvCategoryProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategoryProducts.adapter = adapter
    }
}