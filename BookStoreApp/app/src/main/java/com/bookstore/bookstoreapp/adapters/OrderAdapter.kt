package com.bookstore.bookstoreapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.activities.models.Order
import com.bookstore.bookstoreapp.databinding.OrderItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderAdapter(
    private var orders: List<Order>,
    private val onViewOrderDetails: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size

    /**
     * Güncellenmiş sipariş listesiyle RecyclerView'i verimli bir şekilde güncellemek için DiffUtil kullanılır.
     */
    fun updateOrders(newOrders: List<Order>) {
        val diffCallback = OrderDiffCallback(orders, newOrders)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.orders = newOrders
        diffResult.dispatchUpdatesTo(this)
    }

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            // Sipariş ID'sini ve tarih formatını ayarla
            binding.tvOrderNumber.text = "Order Number: #${order.id}"
            binding.tvOrderDate.text = formatOrderDate(order.orderDate)

            // Toplam fiyatı ayarla
            binding.tvTotalPrice.text = "$${order.totalAmount}"

            // Sipariş detaylarına tıklama işlemi
            binding.root.setOnClickListener { onViewOrderDetails(order) }
        }

        private fun formatOrderDate(orderDate: String): String {
            // Sipariş tarihini formatlayın: Gün-Ay-Yıl Saat
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            return try {
                val date = inputFormat.parse(orderDate)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                orderDate // Format başarısız olursa orijinal tarihi döndür
            }
        }
    }

}

/**
 * DiffUtil ile sipariş listesindeki farkları optimize bir şekilde belirlemek için sınıf.
 */
class OrderDiffCallback(
    private val oldList: List<Order>,
    private val newList: List<Order>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Her siparişi benzersiz yapan ID üzerinden karşılaştırma
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Siparişin içeriği aynı mı
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
