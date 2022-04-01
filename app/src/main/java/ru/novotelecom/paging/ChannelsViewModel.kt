package ru.novotelecom.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow

class ChannelsViewModel(private val loader: ChannelsLoader) : ViewModel() {

    val allChannels: Flow<PagingData<ChannelModel>> = Pager(
        config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = true,
            maxSize = 1000
        )
    ) {
        loader.getChannels()
    }.flow
        .cachedIn(viewModelScope)
}
