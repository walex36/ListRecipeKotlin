package com.example.listreceitasapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listreceitasapp.databinding.ItemFullRecipeBinding
import com.example.listreceitasapp.domain.model.PrepareModeDomain

class PrepareModeAdapter :
    ListAdapter<PrepareModeDomain, PrepareModeAdapter.ViewHolder>(DiffCalBack()) {

    var edit: (PrepareModeDomain) -> Unit = {}
    var remove: (PrepareModeDomain) -> Unit = {}

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
        fun bind(item: PrepareModeDomain) = with(binding) {
            cbCheck.text = item.name
            btnEdit.setOnClickListener {
                edit(item)
            }
            btnRemove.setOnClickListener {
                remove(item)
            }
        }
    }

    class DiffCalBack : DiffUtil.ItemCallback<PrepareModeDomain>() {
        override fun areItemsTheSame(
            oldItem: PrepareModeDomain,
            newItem: PrepareModeDomain,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PrepareModeDomain,
            newItem: PrepareModeDomain,
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}