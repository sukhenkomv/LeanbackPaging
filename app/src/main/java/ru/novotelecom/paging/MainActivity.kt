package ru.novotelecom.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.paging.PagingDataAdapter
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.VerticalGridPresenter

data class ItemObject(
    val id: Int = 0,
    val title: String = ""
)

class MainActivity : AppCompatActivity() {

    lateinit var chlistWrapper: FrameLayout
    lateinit var chlistFragment: VerticalGridSupportFragment
    lateinit var gridPresenter: VerticalGridPresenter
    lateinit var itemPresenter: ChannelListItemHolder

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

        itemPresenter = ChannelListItemHolder()

//        val adapter = ArrayObjectAdapter(itemPresenter)
//        for (i in 1..100) {
//            adapter.add(ItemObject(i, "title $i"))
//        }

        val adapter: PagingDataAdapter<ItemObject> = PagingDataAdapter(presenter = itemPresenter, diffCallback = COMPARATOR)

        chlistFragment.adapter = adapter
        chlistFragment.setSelectedPosition(0)
        chlistWrapper.bringToFront()
        chlistWrapper.requestFocus()
    }
}