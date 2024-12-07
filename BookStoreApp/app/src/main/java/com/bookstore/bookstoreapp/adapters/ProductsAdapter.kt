package com.bookstore.bookstoreapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.Product
import com.bookstore.bookstoreapp.databinding.CardItemBinding


class ProductsAdapter(private val products: List<Product>,
                      private val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(private val binding: CardItemBinding):
            RecyclerView.ViewHolder(binding.root){
            fun bind(product: Product){
                binding.apply {
                    tvProductTitle.text = product.title
                    tvProductAuthor.text = product.author
                    tvProductPrice.text = "${ product.price }₺"
                    imgProduct.setImageResource(product.imageRes)
                }
                binding.root.setOnClickListener {
                    onClick(product)
                }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
