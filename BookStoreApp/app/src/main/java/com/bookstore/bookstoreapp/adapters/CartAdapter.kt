import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.CartItem

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onIncreaseQuantity:(CartItem) -> Unit,
    private val onDecreaseQuantity:(CartItem) -> Unit,
    private val onRemoveFromCart:(CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageCartProduct)
        val productName: TextView = itemView.findViewById(R.id.tvProductCartName)
        val productPrice: TextView = itemView.findViewById(R.id.tvProductCartPrice)
        val tvCartProductQuantity: TextView = itemView.findViewById(R.id.tvCartProductQuantity)
        val imagePlus: ImageView = itemView.findViewById(R.id.imagePlus)
        val imageMinus: ImageView = itemView.findViewById(R.id.imageMinus)
        val authorName : TextView? = itemView.findViewById(R.id.tvAuthorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.productName.text = item.name
        holder.productPrice.text = "${item.price}₺"
        holder.authorName?.text = item.authorName
        holder.tvCartProductQuantity.text = item.quantity.toString()

        holder.imagePlus.setOnClickListener {
            onIncreaseQuantity(item)
        }
        holder.imageMinus.setOnClickListener {
            if (item.quantity > 1) {
                onDecreaseQuantity(item)
            } else {
                onRemoveFromCart(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}
