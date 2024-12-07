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
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        cartViewModel.cartItems.observe(this) { cartItems ->
            val totalQuantity = cartItems.sumOf { it.quantity }

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
            val badge: BadgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.cartFragment)

            if (totalQuantity > 0) {
                badge.isVisible = true
                badge.number = totalQuantity
            } else {
                badge.isVisible = false
            }
        }


        fun getCartViewModel(): CartViewModel{
            return cartViewModel
        }
    }
}