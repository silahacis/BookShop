//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.bookstore.bookstoreapp.data.CartItem
//import com.bookstore.bookstoreapp.helper.CartRepository
//
//class CartViewModel : ViewModel() {
//    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
//    val cartItems: LiveData<MutableList<CartItem>> = _cartItems
//
//    fun addToCart(item: CartItem) {
//        _cartItems.value?.add(item)
//        _cartItems.value = _cartItems.value // Trigger observer
//    }
//}

package com.bookstore.bookstoreapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bookstore.bookstoreapp.data.CartItem
import com.bookstore.bookstoreapp.helper.CartRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CartRepository(application)

    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    init {
        _cartItems.value = mutableListOf()
    }

    fun addToCart(item: CartItem) {
        val currentCart = _cartItems.value ?: mutableListOf()
        val existingItem = currentCart.find { it.bookID == item.bookID }

        if (existingItem != null){
            existingItem.quantity+=item.quantity
        }else{
            currentCart.add(item)
        }
          _cartItems.value = currentCart
    }

    fun increaseQuantity(bookId: Int) {
        val currentCartItems = _cartItems.value ?: return
        val item = currentCartItems.find { it.bookID== bookId }
        item?.let {
            it.quantity += 1
            _cartItems.value = currentCartItems
        }
    }

    fun decreaseQuantity(bookId: Int) {
        val currentCartItems = _cartItems.value ?: return
        val item = currentCartItems.find { it.bookID == bookId }
        item?.let {
            if (it.quantity > 1) {
                it.quantity -= 1
            } else {
                // Remove the item if quantity is 1
                currentCartItems.remove(it)
            }
            _cartItems.value = currentCartItems
        }
    }
    fun updateCartItemQuantity(bookId: Int, newQuantity: Int) {
        val currentCartItems = _cartItems.value ?: return
        val item = currentCartItems.find { it.bookID == bookId }
        item?.let {
            it.quantity = newQuantity
            _cartItems.value = currentCartItems
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        val currentCartItems = _cartItems.value ?: return
        currentCartItems.remove(cartItem)
        _cartItems.value = currentCartItems

    }
}

