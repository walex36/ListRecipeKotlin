package com.example.listreceitasapp.presentation.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listreceitasapp.databinding.ItemRecipeBinding
import com.example.listreceitasapp.domain.model.RecipeDomain

class RecipeAdapter : ListAdapter<RecipeDomain, RecipeAdapter.ViewHolder>(DiffCalBack()) {

    var click: (RecipeDomain) -> Unit = {}

    inner class ViewHolder(
        private val binding: ItemRecipeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeDomain) {
            binding.tvTitle.text = item.name
            binding.root.setOnClickListener{
                click(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCalBack : DiffUtil.ItemCallback<RecipeDomain>() {
    override fun areItemsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain): Boolean {
        return oldItem.id == newItem.id
    }
}