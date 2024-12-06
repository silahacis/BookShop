package com.bookstore.bookstoreapp.data

data class CartItem(
    val bookID : Int,
    val imageResId: Int, // Resource ID for the book image
    val name: String,
    val price: Double,
    var quantity: Int = 1 // Default quantity is 1
)
