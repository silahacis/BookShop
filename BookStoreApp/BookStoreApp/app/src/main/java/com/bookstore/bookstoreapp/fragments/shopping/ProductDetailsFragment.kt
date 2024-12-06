package com.bookstore.bookstoreapp.fragments.shopping


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.CartItem
import com.bookstore.bookstoreapp.data.Product
import com.bookstore.bookstoreapp.databinding.FragmentProductDetailsBinding
import com.bookstore.bookstoreapp.viewmodel.CartViewModel

class ProductDetailsFragment : Fragment() {

    private  var _binding : FragmentProductDetailsBinding? = null
    private val binding get()= _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>("product")

        product?.let {
            displayProductDetails(it)
        } ?: handleProductError()

        // Add to cart button logic
        binding.btnAddToCart.setOnClickListener {
            product?.let {
                val cardItem = CartItem(
                    bookID = it.bookId,
                    imageResId = it.imageRes,
                    name = it.title,
                    price = it.price.toDouble(),
                    quantity = 1
                )
                cartViewModel.addToCart(cardItem)
            } // Add product to cart
        }
        binding.imageClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun displayProductDetails(product: Product){
        binding.tvProductName.text = product.title
        binding.tvProductPrice.text = "${product.price}₺"
        binding.tvAuthorName.text = product.author
        binding.tvProductDescription.text = product.description
        binding.imageProduct.setImageResource(product.imageRes)
    }

    private fun handleProductError(){
        binding.tvProductName.text = getString(R.string.error_product_not_found)
        binding.imageProduct.setImageResource(R.drawable.blury_background)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}