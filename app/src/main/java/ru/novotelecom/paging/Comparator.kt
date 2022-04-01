package ru.novotelecom.paging

import androidx.recyclerview.widget.DiffUtil

val COMPARATOR = object : DiffUtil.ItemCallback<ItemObject>() {
    override fun areItemsTheSame(oldItem: ItemObject, newItem: ItemObject): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ItemObject, newItem: ItemObject): Boolean =
        oldItem == newItem
}
