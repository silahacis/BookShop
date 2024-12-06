package com.bookstore.bookstoreapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): LiveData<List<CartItem>>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}