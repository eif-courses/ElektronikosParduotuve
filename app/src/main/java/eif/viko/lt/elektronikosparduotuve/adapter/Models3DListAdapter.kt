package eif.viko.lt.elektronikosparduotuve.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.elektronikosparduotuve.databinding.ModelListItemBinding
import eif.viko.lt.elektronikosparduotuve.model.Item

class Models3DListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Item, Models3DListAdapter.Models3DViewHolder>(ItemDC()) {

    lateinit var binding: ModelListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Models3DViewHolder {
        binding =
            ModelListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Models3DViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: Models3DViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Item>) {
        submitList(data.toMutableList())
    }

    inner class Models3DViewHolder(
        private val binding: ModelListItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.modelPlaySoundBtn.setOnClickListener(this)
            binding.modelAddToCart.setOnClickListener(this)
            binding.modelView3DBtn.setOnClickListener(this)
            binding.modelViewDetails.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)

            when (v) {
                binding.modelPlaySoundBtn -> interaction?.playSound(clicked)
                binding.modelAddToCart -> interaction?.addToCart(clicked)
                binding.modelView3DBtn -> interaction?.preview3D(clicked)
                binding.modelViewDetails -> interaction?.viewDetails(clicked)
                else -> interaction?.item_clicked(clicked)
            }
        }

        fun bind(item: Item) = with(binding) {
            modelTitle.text = item.title
            modelDescription.text = item.description
            Picasso.get().load(item.poster).into(modelImage)
            // TODO: Bind the data with View
        }
    }

    interface Interaction {
        fun item_clicked(item: Item)
        fun preview3D(item: Item)
        fun playSound(item: Item)
        fun addToCart(item: Item)
        fun viewDetails(item: Item)

    }

    private class ItemDC : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ) = oldItem.audio == newItem.audio

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ) = oldItem == newItem
    }
}