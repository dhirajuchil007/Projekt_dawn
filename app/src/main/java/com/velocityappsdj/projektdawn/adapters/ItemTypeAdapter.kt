package com.velocityappsdj.projektdawn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ItemTypeListItemBinding
import com.velocityappsdj.projektdawn.model.ItemType
import com.velocityappsdj.projektdawn.model.Project


class ItemTypeAdapter(var itemTypes: List<ItemType>, var listener: ItemTypeClickListener) :
    RecyclerView.Adapter<ItemTypeAdapter.ItemTypeViewHolder>() {
    interface ItemTypeClickListener {
        fun onClick(itemType: ItemType);
    }

    class ItemTypeViewHolder(private var itemTypeListItemBinding: ItemTypeListItemBinding) :
        RecyclerView.ViewHolder(itemTypeListItemBinding.root) {

        fun bind(itemType: ItemType) {
            itemTypeListItemBinding.itemType = itemType
            itemTypeListItemBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTypeViewHolder {
        var itemtypeListItemBinding: ItemTypeListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_type_list_item, parent, false
        )

        return ItemTypeViewHolder(itemtypeListItemBinding)
    }

    override fun onBindViewHolder(holder: ItemTypeViewHolder, position: Int) {
        val itemType = itemTypes[position]
        holder.bind(itemType)
        holder.itemView.setOnClickListener {
            listener.onClick(itemType)
        }
    }

    override fun getItemCount(): Int {
        return itemTypes.size
    }
}