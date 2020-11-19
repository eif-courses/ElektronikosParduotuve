package eif.viko.lt.elektronikosparduotuve.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.elektronikosparduotuve.databinding.ProductItemLayoutBinding
import eif.viko.lt.elektronikosparduotuve.model.Product


class ProductListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductDC()) {

    lateinit var binding: ProductItemLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding =
            ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, interaction)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun swapData(data: List<Product>) {
        submitList(data.toMutableList())
    }

    inner class ProductViewHolder(
        private val binding: ProductItemLayoutBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.btnAddToCart.setOnClickListener(this)
            binding.btnDetails.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)


            // ATNAUJINTAS switch sakinys kotlin kalboje
                when (v) {
                    binding.btnAddToCart -> interaction?.addToCart(clicked)
                    binding.btnDetails -> interaction?.viewDetails(clicked)
                    else -> interaction?.clickOnItem(clicked)
                }


        }

        fun bind(item: Product) = with(binding) {
            // TODO: Bind the data with View
            productTitle.text = item.title
            productPrice.text = item.price.toString()
            Picasso.get().load(item.imageURL).into(productImage)

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