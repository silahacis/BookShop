package com.bookstore.bookstoreapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val bookID : Int,
    val imageResId: Int, // Resource ID for the book image
    val authorName: String,
    val name: String,
    val price: Double,
    var quantity: Int = 1 // Default quantity is 1
): Parcelable
