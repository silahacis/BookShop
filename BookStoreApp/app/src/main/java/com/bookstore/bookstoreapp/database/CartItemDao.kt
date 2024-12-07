package com.bookstore.bookstoreapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItemDatabase: CartItemDatabase)

    @Delete
    suspend fun delete(cartItemDatabase: CartItemDatabase)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): LiveData<List<CartItemDatabase>>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}