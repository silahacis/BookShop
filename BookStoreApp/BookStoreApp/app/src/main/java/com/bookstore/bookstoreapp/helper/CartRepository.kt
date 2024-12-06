package com.bookstore.bookstoreapp.helper

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bookstore.bookstoreapp.data.CartItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartRepository(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("cart_preferences", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun getCartItems(): MutableList<CartItem> {
        val cartJson = sharedPreferences.getString("cart_items", null)
        return if (cartJson.isNullOrEmpty()) {
            mutableListOf()
        } else {
            val type = object : TypeToken<MutableList<CartItem>>() {}.type
            gson.fromJson(cartJson, type)
        }
    }

    fun saveCartItems(cartItems: MutableList<CartItem>) {
        val cartJson = gson.toJson(cartItems)
        sharedPreferences.edit().putString("cart_items", cartJson).apply()
    }
}
