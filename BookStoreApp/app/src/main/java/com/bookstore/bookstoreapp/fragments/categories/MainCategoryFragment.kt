package com.bookstore.bookstoreapp.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.adapters.ProductsAdapter
import com.bookstore.bookstoreapp.databinding.FragmentMainCategoryBinding
import com.bookstore.bookstoreapp.helper.ProductRepository

class MainCategoryFragment : Fragment() {


    private var _binding: FragmentMainCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter : ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = ProductRepository.getAllProducts()
        productAdapter = ProductsAdapter(products){product  ->
            val bundle = Bundle().apply {
                putParcelable("product", product)
            }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle)
        }
        binding.rvBestProducts.adapter = productAdapter
        //val products = ProductRepository.ProductRepository.getAllProducts()

        // Setup RecyclerView with GridLayoutManager and Adapter
        binding.rvBestProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBestProducts.adapter = productAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
