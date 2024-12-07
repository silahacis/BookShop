package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookstore.bookstoreapp.activities.RetrofitClient
import com.bookstore.bookstoreapp.activities.models.ApiResponse
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.adapters.OrderAdapter
import com.bookstore.bookstoreapp.databinding.FragmentOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvAllOrders
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adapter'i başlat ve RecyclerView'e ata
        orderAdapter = OrderAdapter(emptyList()) { order ->
            // Click işlemleri burada olacak
        }
        recyclerView.adapter = orderAdapter

        fetchOrders()
    }

    private fun fetchOrders() {
        val apiService = RetrofitClient.instance
        val customerId = 1 // Örnek müşteri ID'si
        val call = apiService.getOrdersByCustomerId(customerId)

        call.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    val orders = response.body()
                    println("Orders: $orders")
                    if (!orders.isNullOrEmpty()) {
                        binding.tvEmptyOrders.visibility = View.GONE
                        orderAdapter.updateOrders(orders)
                    } else {
                        binding.tvEmptyOrders.visibility = View.VISIBLE
                    }
                } else {
                    println("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                println("Failed to fetch orders: ${t.message}")
            }
        })
    }
}
