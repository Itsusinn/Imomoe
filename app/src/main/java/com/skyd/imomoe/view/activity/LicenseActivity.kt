package com.skyd.imomoe.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyd.imomoe.R
import com.skyd.imomoe.bean.LicenseBean
import com.skyd.imomoe.config.Const
import com.skyd.imomoe.view.adapter.LicenseAdapter
import kotlinx.android.synthetic.main.activity_license.*
import kotlinx.android.synthetic.main.layout_toolbar_1.*


class LicenseActivity : AppCompatActivity() {
    private val list: MutableList<LicenseBean> = ArrayList()
    private val adapter: LicenseAdapter = LicenseAdapter(this, list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        tv_toolbar_1_title.text = getString(R.string.open_source_licenses)
        iv_toolbar_1_back.setOnClickListener { finish() }
        rv_license_activity.layoutManager = LinearLayoutManager(this)
        rv_license_activity.adapter = adapter

        list.add(LicenseBean("licenseHeader1", "", "", "", ""))
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/jhy/jsoup",
                "jsoup",
                "MIT License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/bumptech/glide",
                "glide",
                "BSD, part MIT and Apache 2.0"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/nex3z/FlowLayout",
                "FlowLayout",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/CarGuo/GSYVideoPlayer",
                "GSYVideoPlayer",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/square/okhttp",
                "okhttp",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/square/retrofit",
                "retrofit",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/ReactiveX/RxJava",
                "RxJava",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/getActivity/XXPermissions",
                "XXPermissions",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/Kotlin/kotlinx.coroutines",
                "kotlinx.coroutines",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/afollestad/material-dialogs",
                "material-dialogs",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/lingochamp/FileDownloader",
                "FileDownloader",
                "Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/4thline/cling",
                "cling",
                "LGPL License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/eclipse/jetty.project",
                "jetty.project",
                "EPL-2.0, Apache-2.0 License"
            )
        )
        list.add(
            LicenseBean(
                "license1",
                Const.ActionUrl.ANIME_BROWSER,
                "https://github.com/NanoHttpd/nanohttpd",
                "nanohttpd",
                "BSD-3-Clause License"
            )
        )
        adapter.notifyDataSetChanged()
    }
}