package com.bookstore.bookstoreapp.fragments.shopping


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

                // Disable button temporarily to prevent multiple clicks
                binding.btnAddToCart.isEnabled = false
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.btnAddToCart.isEnabled = true
                }, 1000) // 1-second cooldown

                showAddToCartAnimation()

                val toast = Toast.makeText(requireContext(), "Book is added to cart", Toast.LENGTH_SHORT)
                val view = toast.view
                view?.setBackgroundResource(R.drawable.icon_background) // Use a drawable or color resource
                toast.show()
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

    private fun showAddToCartAnimation() {
        val scaleX = ObjectAnimator.ofFloat(binding.btnAddToCart, "scaleX", 1f, 1.1f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.btnAddToCart, "scaleY", 1f, 1.1f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}