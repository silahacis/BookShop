package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.activities.RetrofitClient
import com.bookstore.bookstoreapp.activities.models.Book
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.databinding.FragmentBillingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class BillingFragment : Fragment() {

    private lateinit var binding: FragmentBillingBinding
    private val handler = android.os.Handler()
    private lateinit var updateRunnable: Runnable
    private var orderId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderId = arguments?.getInt("ORDER_ID") ?: run {
            showErrorMessage("Invalid Order ID")
            return
        }

        startPeriodicUpdate()
    }

    private fun startPeriodicUpdate() {
        updateRunnable = Runnable {
            orderId?.let { fetchOrderDetails(it) }
            handler.postDelayed(updateRunnable, 4000)
        }
        handler.post(updateRunnable)
    }

    private fun stopPeriodicUpdate() {
        handler.removeCallbacks(updateRunnable)
    }

    private fun fetchOrderDetails(orderId: Int) {
        val apiService = RetrofitClient.instance
        apiService.getOrderByOrderId(orderId).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if (response.isSuccessful) {
                    response.body()?.let { order ->
                        populateOrderDetails(order)
                    } ?: showErrorMessage("Order details not found.")
                } else {
                    showErrorMessage("Failed to load order details.")
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                showErrorMessage("Error: ${t.localizedMessage}")
            }
        })
    }

    private fun populateOrderDetails(order: Order) {
        with(binding) {
            tvOrderId.text = "#${order.id ?: "N/A"}"
            tvOrderAddress.text = order.orderAddress ?: "N/A"
            tvTotalPrice.text = String.format("%.2f", order.totalAmount ?: 0.00)

            tvOrderDate.text = formatDate(order.orderDate ?: "No order date available.")

            if (order.orderMessages.isNullOrEmpty()) {
                tvOrderMessages.text = "No order messages available."
            } else {
                tvOrderMessages.text = order.orderMessages.joinToString(separator = "\n") { it }
            }

            val books = order.books
            if (books.isNullOrEmpty()) {
                showErrorMessage("No products available.")
            } else {
                populateProductsRecyclerView(books)
            }
        }
    }

    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val parsedDate = inputFormat.parse(date)
        return parsedDate?.let { outputFormat.format(it) } ?: "--/--/---- --:--"
    }

    private fun populateProductsRecyclerView(books: List<Book>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.layoutManager = layoutManager
        binding.rvProducts.adapter = ProductAdapter(books)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopPeriodicUpdate()
    }
}


class ProductAdapter(private val books: List<Book>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.tvBookTitle)
        private val productAuthorTextView: TextView = itemView.findViewById(R.id.tvAuthorName)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.tvBookPrice)

        fun bind(book: Book) {
            // Null check for the book details to avoid NullPointerException
            productNameTextView.text = book.title ?: "Unknown Title"
            productAuthorTextView.text = book.author ?: "Unknown Author"
            productPriceTextView.text = String.format("%.2f", book.price ?: 0.00)
        }
    }
}
