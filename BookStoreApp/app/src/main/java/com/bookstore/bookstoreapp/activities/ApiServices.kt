package com.bookstore.bookstoreapp.activities


import com.bookstore.bookstoreapp.activities.models.ApiResponse
import com.bookstore.bookstoreapp.activities.models.CreateOrderRequest
import com.bookstore.bookstoreapp.activities.models.Order
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiServices {

    @GET("api/Orders/customer/{customerId}")
    fun getOrdersByCustomerId(@Path("customerId") customerId: Int): Call<List<Order>>

    @GET("api/Orders/{orderId}")
    fun getOrderByOrderId(@Path("orderId") orderId: Int): Call<Order>

    @POST("api/Orders")
    fun createOrder(@Body request: CreateOrderRequest): Call<Order>

    @POST("api/Orders/{orderId}/simulate")
    fun simulateOrder(@Path("orderId") orderId: Int): Call<ApiResponse>

}