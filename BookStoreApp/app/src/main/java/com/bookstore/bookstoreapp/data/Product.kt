package com.bookstore.bookstoreapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val bookId: Int,
    val imageRes: Int,
    val title: String,
    val author: String,
    val price: String,
    val category: String,
    val description: String
): Parcelable
