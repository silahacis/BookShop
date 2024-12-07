package com.bookstore.bookstoreapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id: String,
    val date: String,
    val items: List<CartItem>
) : Parcelable
