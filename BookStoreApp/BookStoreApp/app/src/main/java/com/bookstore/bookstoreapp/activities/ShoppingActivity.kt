package com.bookstore.bookstoreapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.databinding.ActivityShoppingBinding
import com.bookstore.bookstoreapp.viewmodel.CartViewModel

class ShoppingActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_,destination,_->
            if(destination.id == R.id.productDetailsFragment){
                binding.bottomNavigation.visibility = View.GONE
            }else{
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        fun getCartViewModel(): CartViewModel{
            return cartViewModel
        }
    }
}