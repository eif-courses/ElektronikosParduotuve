package eif.viko.lt.elektronikosparduotuve.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.elektronikosparduotuve.R
import eif.viko.lt.elektronikosparduotuve.model.Product
import kotlinx.android.synthetic.main.fragment_view_details.view.*
import kotlinx.android.synthetic.main.product_item_layout.view.*


class ProductListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false), interaction
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    fun swapData(data: List<Product>) {
        submitList(data.toMutableList())
    }

    inner class ProductViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.btn_add_to_cart.setOnClickListener(this)
            itemView.btn_details.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)


            when (v) {
                itemView.btn_add_to_cart -> interaction?.addToCart(clicked)
                itemView.btn_details -> interaction?.viewDetails(clicked)
                else -> interaction?.clickOnItem(clicked)
            }

        }

        fun bind(item: Product) = with(itemView) {
            // TODO: Bind the data with View
            product_title.text = item.title
            product_price.text = item.price.toString()
            Picasso.get().load(item.imageURL).into(product_image)

        }
    }

    interface Interaction {
        fun addToCart(product: Product)
        fun viewDetails(product: Product)
        fun clickOnItem(product: Product)
    }

    private class ProductDC : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ) = oldItem.id == newItem.id
    }
}