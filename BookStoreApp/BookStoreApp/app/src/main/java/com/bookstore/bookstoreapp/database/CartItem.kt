package com.bookstore.bookstoreapp.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageResId: Int,
    val name: String,
    val price: Double,
    val quantity: Int = 1
)
