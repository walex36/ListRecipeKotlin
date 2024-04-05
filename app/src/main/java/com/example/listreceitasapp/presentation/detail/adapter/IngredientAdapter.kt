package com.example.listreceitasapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listreceitasapp.databinding.ItemFullRecipeBinding
import com.example.listreceitasapp.domain.model.IngredientDomain

class IngredientAdapter :
    ListAdapter<IngredientDomain, IngredientAdapter.ViewHolder>(DiffCalBack()) {

    var edit: (IngredientDomain) -> Unit = {}
    var remove: (IngredientDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemFullRecipeBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemFullRecipeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IngredientDomain) = with(binding) {
            cbCheck.text = item.name
            btnEdit.setOnClickListener {
                edit(item)
            }
            btnRemove.setOnClickListener {
                remove(item)
            }
        }
    }

    class DiffCalBack : DiffUtil.ItemCallback<IngredientDomain>() {
        override fun areItemsTheSame(
            oldItem: IngredientDomain,
            newItem: IngredientDomain,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: IngredientDomain,
            newItem: IngredientDomain,
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}