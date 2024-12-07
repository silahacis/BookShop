package com.bookstore.bookstoreapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bookstore.bookstoreapp.activities.models.ApiResponse
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.activities.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val _orderItems = MutableLiveData<List<Order>>()
    val orderItems: LiveData<List<Order>> = _orderItems
}
