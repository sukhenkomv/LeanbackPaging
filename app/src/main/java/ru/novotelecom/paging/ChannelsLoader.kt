package ru.novotelecom.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Math.min

class ChannelsLoader {

    companion object {
        val MAX_SIZE = 150
    }

    var alreadyLoaded = 0

    fun getChannels(): PagingSource<Int, ChannelModel> {
        return object : PagingSource<Int, ChannelModel>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChannelModel> {
                val pageNumber = params.key ?: 1
                val pageSize = params.loadSize
                val channels = ArrayList<ChannelModel>()

                val firstN = alreadyLoaded + 1
                val lastN = alreadyLoaded + pageSize

                for (i in firstN..lastN) {
                    channels.add(ChannelModel(i, "Channel $i"))
                }

                alreadyLoaded = lastN

                return LoadResult.Page(
                    data = channels as List<ChannelModel>,
                    prevKey = null,
                    nextKey = if (lastN < MAX_SIZE) { pageNumber + 1} else {null}
                )
            }

            override fun getRefreshKey(state: PagingState<Int, ChannelModel>): Int? = null
        }
    }
}
