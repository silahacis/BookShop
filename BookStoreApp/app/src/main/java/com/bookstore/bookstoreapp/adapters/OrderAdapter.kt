package com.bookstore.bookstoreapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.databinding.OrderItemBinding
import com.bookstore.bookstoreapp.activities.models.Order
import java.text.SimpleDateFormat
import java.util.Locale

class OrderAdapter(
    private var orders: List<Order>,
    private val onOrderClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrderItemBinding.inflate(inflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
        holder.itemView.setOnClickListener { onOrderClick(order) }
    }

    override fun getItemCount(): Int = orders.size

    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }

    class OrderViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.tvOrderNumber.text = "#${order.id}"
            binding.tvTotalPrice.text = "$${order.totalAmount}"
            binding.tvOrderDate.text = formatDate(order.orderDate)
            binding.tvPaymentType.text = "Payment Type: ${order.paymentMethod}"
            binding.tvInvoiceType.text = "Invoice Type: ${order.invoiceType}"
        }

        private fun formatDate(date: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            val parsedDate = inputFormat.parse(date)
            return parsedDate?.let { outputFormat.format(it) } ?: "--/--/---- --:--"
        }

    }
}