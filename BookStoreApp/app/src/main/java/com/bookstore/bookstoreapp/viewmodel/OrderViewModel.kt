package com.bookstore.bookstoreapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bookstore.bookstoreapp.data.CartItem
import com.bookstore.bookstoreapp.data.Order
import com.bookstore.bookstoreapp.helper.OrderRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OrderRepository(application)

    private val _orderItems = MutableLiveData<MutableList<Order>>()
    val orderItems: LiveData<MutableList<Order>> = _orderItems

    fun addOrder(orders: List<CartItem>): Order? {
        if (orders.isEmpty()) return null

        val orderId = UUID.randomUUID().toString() // Generate a unique order ID
//        val totalPrice = cartItems.sumOf { it.quantity * it.price }
//        val timestamp = System.currentTimeMillis()

        val order = Order(
            id = orderId,
            items = orders,
            date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
//            totalPrice = totalPrice,
//            timestamp = timestamp
        )

        repository.saveOrder(order)
        return order
    }

    fun updateOrders(newOrders: List<Order>) {
        _orderItems.value = newOrders.toMutableList()
    }
}
