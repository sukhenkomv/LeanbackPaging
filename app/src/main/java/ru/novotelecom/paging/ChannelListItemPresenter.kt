package ru.novotelecom.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter

class ChannelListItemPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.channels_list_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        (viewHolder as ItemHolder).bind(item)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {}
}

class ItemHolder(itemView: View) : Presenter.ViewHolder(itemView) {

    var titleText: TextView = view.findViewById(R.id.channels_list_item_title_tv)

    fun bind(
        item: Any?
    ) {
        val channelTitle: String = (item as ItemObject).title
        titleText.text = channelTitle
    }
}