package com.bookstore.bookstoreapp.helper

import android.content.Context
import com.bookstore.bookstoreapp.data.Order

class OrderRepository(private val context: Context) {

    // Simulated method to save an order (replace with actual database or API implementation)
    fun saveOrder(order: Order) {
        // Save the order to a database or API
        println("Order saved: $order")
    }
}
