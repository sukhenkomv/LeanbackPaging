package ru.novotelecom.paging

import androidx.recyclerview.widget.DiffUtil

val COMPARATOR = object : DiffUtil.ItemCallback<ChannelModel>() {
    override fun areItemsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean =
        oldItem == newItem
}
