package com.mozio.pizzadelivery.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import com.mozio.pizzadelivery.databinding.ItemFlavorBinding

/**
 * Adapter used for listing the possible flavors to be selected when making an order
 */
class FlavorAdapter(
    private val flavors: MutableList<PizzaFlavor> = mutableListOf(),
    private val selectedFlavors: MutableList<PizzaFlavor> = mutableListOf(),
    var onFlavorAddedListener: (flavor: PizzaFlavor) -> Unit = {},
    var onFlavorRemovedListener: (flavor: PizzaFlavor) -> Unit = {}
) : RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorViewHolder =
        FlavorViewHolder.from(parent)

    override fun onBindViewHolder(holder: FlavorViewHolder, position: Int) {
        val item = flavors[position]
        holder.bind(item, onFlavorAddedListener, onFlavorRemovedListener)
    }

    override fun getItemCount(): Int = flavors.size

    fun updateFlavors(flavors: List<PizzaFlavor>) {
        this.flavors.clear()
        this.flavors.addAll(flavors)
        notifyDataSetChanged()
    }

    class FlavorViewHolder(private val binding: ItemFlavorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): FlavorViewHolder {
                val binding: ItemFlavorBinding = ItemFlavorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FlavorViewHolder(binding)
            }
        }

        fun bind(item: PizzaFlavor,
                 onFlavorAddedListener: (flavor: PizzaFlavor) -> Unit,
                 onFlavorRemovedListener: (flavor: PizzaFlavor) -> Unit) {
            binding.flavorName.text = item.name
            binding.flavorPrice.text = "$ ${item.price}"
            binding.flavorCheckbox.setOnClickListener {
                if (binding.flavorCheckbox.isChecked) {
                    onFlavorAddedListener(item)
                } else {
                    onFlavorRemovedListener(item)
                }
            }
        }
    }
}