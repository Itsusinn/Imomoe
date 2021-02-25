package com.skyd.imomoe.view.activity

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.skyd.imomoe.R
import com.skyd.imomoe.util.BlurUtils.blur
import com.skyd.imomoe.util.glide.GlideUtil.getGlideUrl
import com.skyd.imomoe.util.Util.getStatusBarHeight
import com.skyd.imomoe.util.Util.setTransparentStatusBar
import com.skyd.imomoe.view.adapter.AnimeDetailAdapter
import com.skyd.imomoe.viewmodel.AnimeDetailViewModel
import kotlinx.android.synthetic.main.activity_anime_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_1.*


class AnimeDetailActivity : BaseActivity() {
    private var partUrl: String = ""
    private lateinit var viewModel: AnimeDetailViewModel
    private lateinit var adapter: AnimeDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        setTransparentStatusBar(window, isDark = true)

        val statusBarLinearParams = view_anime_detail_activity_status_bar.layoutParams //取控件当前的布局参数
        statusBarLinearParams.height = getStatusBarHeight()
        view_anime_detail_activity_status_bar.layoutParams = statusBarLinearParams

        viewModel = ViewModelProvider(this).get(AnimeDetailViewModel::class.java)
        adapter = AnimeDetailAdapter(this, viewModel.animeDetailList)

        partUrl = intent.getStringExtra("partUrl") ?: ""

        ll_anime_detail_activity_toolbar.setBackgroundColor(Color.TRANSPARENT)
        tv_toolbar_1_title.isFocused = true
        iv_toolbar_1_back.setOnClickListener { finish() }

        rv_anime_detail_activity_info.layoutManager = LinearLayoutManager(this)
        rv_anime_detail_activity_info.adapter = adapter

        srl_anime_detail_activity.setOnRefreshListener { viewModel.getAnimeDetailData(partUrl) }
        srl_anime_detail_activity.setColorSchemeResources(R.color.main_color)

        viewModel.mldAnimeDetailList.observe(this, Observer {
            srl_anime_detail_activity.isRefreshing = false

            //先隐藏
            ObjectAnimator.ofFloat(iv_anime_detail_activity_background, "alpha", 1f, 0f)
                .setDuration(250).start()
            val glideUrl = getGlideUrl(viewModel.cover.url, viewModel.cover.referer)
            Glide.with(this).asBitmap().load(glideUrl).dontAnimate().into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val bitmapDrawable = BitmapDrawable(null, blur(resource))
                    bitmapDrawable.colorFilter = PorterDuffColorFilter(
                        Color.LTGRAY, PorterDuff.Mode.MULTIPLY
                    )
                    iv_anime_detail_activity_background.setImageDrawable(bitmapDrawable)

                    //加载完背景图再显示
                    ObjectAnimator.ofFloat(iv_anime_detail_activity_background, "alpha", 0f, 1f)
                        .setDuration(250).start()
                }
            })
            tv_toolbar_1_title.text = viewModel.title
            adapter.notifyDataSetChanged()
        })

        srl_anime_detail_activity.isRefreshing = true
        viewModel.getAnimeDetailData(partUrl)
    }
}