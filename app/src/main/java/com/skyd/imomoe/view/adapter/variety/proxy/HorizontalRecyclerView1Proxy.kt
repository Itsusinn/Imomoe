package com.skyd.imomoe.view.adapter.variety.proxy

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.skyd.imomoe.R
import com.skyd.imomoe.bean.AnimeEpisode1Bean
import com.skyd.imomoe.bean.HorizontalRecyclerView1Bean
import com.skyd.imomoe.ext.theme.getAttrColor
import com.skyd.imomoe.util.AnimeEpisode1ViewHolder
import com.skyd.imomoe.util.HorizontalRecyclerView1ViewHolder
import com.skyd.imomoe.util.compare.EpisodeTitleSort.sortEpisodeTitle
import com.skyd.imomoe.view.adapter.decoration.HorizontalRecyclerViewDecoration
import com.skyd.imomoe.view.adapter.variety.VarietyAdapter

class HorizontalRecyclerView1Proxy(
    @IntRange(from = 0, to = 1) private val color: Int = THEME,
    private val onMoreButtonClickListener: ((
        holder: HorizontalRecyclerView1ViewHolder,
        data: HorizontalRecyclerView1Bean,
        index: Int
    ) -> Unit)? = null,
    private val onAnimeEpisodeClickListener: ((
        holder: AnimeEpisode1ViewHolder,
        data: AnimeEpisode1Bean,
        index: Int
    ) -> Unit)? = null,
    private val animeEpisodeHeight: Int? = null,
    private val animeEpisodeWidth: Int? = null
) : VarietyAdapter.Proxy<HorizontalRecyclerView1Bean, HorizontalRecyclerView1ViewHolder>() {
    companion object {
        const val THEME = 0
        const val WHITE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HorizontalRecyclerView1ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_horizontal_recycler_view_1, parent, false)
        )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: HorizontalRecyclerView1ViewHolder,
        data: HorizontalRecyclerView1Bean,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        when (color) {
            THEME -> {
                holder.ivHorizontalRecyclerView1More
                    .setImageResource(R.drawable.ic_keyboard_arrow_down_24)
                holder.ivHorizontalRecyclerView1More.imageTintList = ColorStateList.valueOf(
                    holder.itemView.context.getAttrColor(R.attr.colorPrimary)
                )
            }
            WHITE -> {
                holder.ivHorizontalRecyclerView1More
                    .setImageResource(R.drawable.ic_keyboard_arrow_down_24)
                holder.ivHorizontalRecyclerView1More.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, android.R.color.white)
                )
            }
        }
        holder.rvHorizontalRecyclerView1.apply {
            if (itemDecorationCount == 0) addItemDecoration(HorizontalRecyclerViewDecoration())
            if (adapter == null) {
                adapter = VarietyAdapter(
                    mutableListOf(
                        AnimeEpisode1Proxy(
                            onClickListener = onAnimeEpisodeClickListener,
                            height = animeEpisodeHeight,
                            width = animeEpisodeWidth
                        )
                    )
                ).apply { dataList = data.episodeList.toMutableList().sortEpisodeTitle() }
            } else adapter?.notifyDataSetChanged()
        }
        holder.ivHorizontalRecyclerView1More.setOnClickListener {
            onMoreButtonClickListener?.invoke(holder, data, index)
        }
    }
}