package ru.novotelecom.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.paging.PagingDataAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.VerticalGridPresenter
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var chlistWrapper: FrameLayout
    lateinit var chlistFragment: VerticalGridSupportFragment
    lateinit var gridPresenter: VerticalGridPresenter
    lateinit var itemPresenter: ChannelListItemPresenter

    private val viewModel by viewModels<ChannelsViewModel> { ChannelsViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chlistWrapper = findViewById(R.id.player_activity_channels_list_wrapper_fl)

        chlistFragment =
            supportFragmentManager.findFragmentById(R.id.player_activity_channels_list_fr) as VerticalGridSupportFragment

        chlistFragment.setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item != null) {
            }
        }

        chlistFragment.setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
        }

        gridPresenter = VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_SMALL, true)
        gridPresenter.numberOfColumns = 1
        gridPresenter.shadowEnabled = true
        chlistFragment.gridPresenter = gridPresenter

        itemPresenter = ChannelListItemPresenter()

//        val adapter = ArrayObjectAdapter(itemPresenter)
//        for (i in 1..100) {
//            adapter.add(ItemObject(i, "title $i"))
//        }

        val adapter: PagingDataAdapter<ChannelModel> =
            PagingDataAdapter(presenter = itemPresenter, diffCallback = COMPARATOR)

        chlistFragment.adapter = adapter

        lifecycleScope.launch {
            viewModel.allChannels.collectLatest {
                adapter.submitData(it)
            }
        }

        chlistFragment.setSelectedPosition(0)
        chlistWrapper.bringToFront()
        chlistWrapper.requestFocus()
    }
}