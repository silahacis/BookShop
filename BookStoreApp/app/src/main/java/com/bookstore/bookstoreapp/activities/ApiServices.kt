package com.bookstore.bookstoreapp.activities


import com.bookstore.bookstoreapp.activities.models.ApiResponse
import com.bookstore.bookstoreapp.activities.models.Order
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {

    @GET("api/Orders/customer/{customerId}")
    fun getOrdersByCustomerId(@Path("customerId") customerId: Int): Call<List<Order>>

}