package com.bookstore.bookstoreapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.Order

class OrderAdapter(
    private val orders: List<Order>,
    private val onViewOrderDetails: (Order) -> Unit
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>()
{
    inner class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvOrderId: TextView = itemView.findViewById(R.id.tvOrderId)
        val tvOrderDate : TextView = itemView.findViewById(R.id.tvOrderDate)

        fun bind(order: Order){
            tvOrderId.text = order.id.toString()
            tvOrderDate.text = order.date.toString()
        }
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
//        holder.bind(orders[position])
        holder.tvOrderId.text = "Order ID: ${order.id}"
        holder.tvOrderDate.text = "Order Date: ${order.date}"
        holder.itemView.setOnClickListener {
            onViewOrderDetails(order)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }



    override fun getItemCount(): Int {
        return orders.size
    }

}
