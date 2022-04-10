package com.skyd.imomoe.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skyd.imomoe.R
import com.skyd.imomoe.config.Const
import com.skyd.imomoe.config.Route
import com.skyd.imomoe.databinding.ActivityMainBinding
import com.skyd.imomoe.ext.fitsSystemWindows2
import com.skyd.imomoe.ext.initUM
import com.skyd.imomoe.ext.showMessageDialog
import com.skyd.imomoe.ext.toHtml
import com.skyd.imomoe.model.DataSourceManager
import com.skyd.imomoe.util.Util.getUserNoticeContent
import com.skyd.imomoe.util.Util.lastReadUserNoticeVersion
import com.skyd.imomoe.util.Util.process
import com.skyd.imomoe.util.Util.setReadUserNoticeVersion
import com.skyd.imomoe.util.eventbus.EventBusSubscriber
import com.skyd.imomoe.util.eventbus.MessageEvent
import com.skyd.imomoe.util.eventbus.SelectHomeTabEvent
import com.skyd.imomoe.util.logE
import com.skyd.imomoe.util.registerShortcuts
import com.skyd.imomoe.util.showToast
import com.skyd.imomoe.util.update.AppUpdateHelper
import com.skyd.imomoe.util.update.AppUpdateStatus
import com.skyd.imomoe.view.fragment.EverydayAnimeFragment
import com.skyd.imomoe.view.fragment.HomeFragment
import com.skyd.imomoe.view.fragment.MoreFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity<ActivityMainBinding>(), EventBusSubscriber {
    private var backPressTime = 0L
    private val adapter: VpAdapter by lazy { VpAdapter(this) }
    private lateinit var action: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (lastReadUserNoticeVersion() < Const.Common.USER_NOTICE_VERSION) {
            showMessageDialog(
                title = getString(R.string.user_notice_update),
                message = getUserNoticeContent().toHtml(),
                cancelable = false,
                positiveText = getString(R.string.agree),
                onPositive = { _, _ ->
                    setReadUserNoticeVersion(Const.Common.USER_NOTICE_VERSION)
                    initUM()
                    initData()
                },
                negativeText = getString(R.string.disagree_and_exit),
                onNegative = { _, _ -> finish() }
            )
        } else initData()
    }

    private fun initData() {
        doIntent(intent)
        action = intent.action.orEmpty()

        // 检查更新
        val appUpdateHelper = AppUpdateHelper.instance
        appUpdateHelper.getUpdateStatus().observe(this) {
            when (it) {
                AppUpdateStatus.UNCHECK -> appUpdateHelper.checkUpdate()
                AppUpdateStatus.DATED -> appUpdateHelper.noticeUpdate(this)
                else -> Unit
            }
        }

        mBinding.vp2MainActivity.also {
            it.adapter = adapter
            it.fitsSystemWindows2()
            it.offscreenPageLimit = adapter.itemCount
            it.isUserInputEnabled = false
        }

        mBinding.bnvMainActivity.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_fragment -> {
                    mBinding.vp2MainActivity.setCurrentItem(0, false)
                    true
                }
                R.id.everyday_anime_fragment -> {
                    mBinding.vp2MainActivity.setCurrentItem(1, false)
                    true
                }
                R.id.more_fragment -> {
                    mBinding.vp2MainActivity.setCurrentItem(2, false)
                    true
                }
                else -> {
                    false
                }
            }
        }

        registerShortcuts()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        doIntent(intent)
    }

    private fun doIntent(intent: Intent?) {
        val uri: Uri = intent?.data ?: return
        runCatching {
            val host: String = uri.host.orEmpty()
            val scheme: String = uri.scheme.orEmpty()
            if (scheme == Route.SCHEME) {
                if (host == Route.ROUTE_OPEN_APP.host) {
                    val url: String = uri.getQueryParameter("pageUrl").orEmpty()
                    process(this, url)
                }
            }
        }.onFailure {
            logE(it.message.toString())
            it.message?.showToast()
        }
    }

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun processBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            getString(R.string.press_again_to_exit).showToast()
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            processBackPressed()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun onMessageEvent(event: MessageEvent) {
        when (event) {
            is SelectHomeTabEvent -> {
                mBinding.vp2MainActivity.setCurrentItem(0, false)
            }
        }
    }

    class VpAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = 3

        override fun createFragment(position: Int) = when (position) {
            0 -> HomeFragment()
            1 -> EverydayAnimeFragment()
            2 -> MoreFragment()
            else -> HomeFragment()
        }
    }
}
