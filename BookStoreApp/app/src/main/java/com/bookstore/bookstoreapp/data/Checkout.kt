package com.bookstore.bookstoreapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checkout(
    val address : String,
    val paymentMethod : String,
    val billMethod : String
):Parcelable
