package com.bookstore.bookstoreapp.activities.models

data class ApiResponse(
    val status: String,
    val data: List<Order>
)

data class Book(
    val id: Int,
    val price: Double,
    val categories: List<Category>,
    val stock: Int,
    val title: String,
    val author: String
)

data class Category(
    val id: Int,
    val name: String
)

data class Order(
    val id: Int,
    val books: List<Book>,
    val totalAmount: Double,
    val orderAddress: String?,
    val orderDate: String,
    val orderMessages: List<String>,
    val invoiceType: String,
    val paymentMethod: String
)

data class GetOrderOrderId(
    val order: Order
)

