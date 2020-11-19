package eif.viko.lt.elektronikosparduotuve.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.elektronikosparduotuve.databinding.CartListItemBinding
import eif.viko.lt.elektronikosparduotuve.model.Product


class CartListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Product, CartListAdapter.CartViewHolder>(ProductDC()) {

    lateinit var binding: CartListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CartViewHolder{

        binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  CartViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Product>) {
        submitList(data.toMutableList())
    }

    inner class CartViewHolder(
        private val binding: CartListItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.removeItemFromCart.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)

            when (v) {
                binding.removeItemFromCart -> interaction?.removeButtonClicked(clicked)
                else -> interaction?.clickButton(clicked)
            }


        }

        fun bind(item: Product) = with(binding) {
            // TODO: Bind the data with View
            cartTitleText.text = item.title
            Picasso.get().load(item.imageURL).into(cartListItemImage)
        }
    }

    interface Interaction {
        fun clickButton(product: Product)
        fun removeButtonClicked(product: Product)
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