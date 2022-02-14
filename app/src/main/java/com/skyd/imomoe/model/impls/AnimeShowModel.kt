package com.skyd.imomoe.model.impls

import com.skyd.imomoe.R
import com.skyd.imomoe.bean.*
import com.skyd.imomoe.config.Const.ActionUrl.Companion.ANIME_BROWSER
import com.skyd.imomoe.model.interfaces.IAnimeShowModel

class AnimeShowModel : IAnimeShowModel {
    override suspend fun getAnimeShowData(
        partUrl: String
    ): Pair<ArrayList<Any>, PageNumberBean?> {
        return Pair(
            arrayListOf(
                Banner1Bean(
                    "",
                    arrayListOf(
                        AnimeCover6Bean(
                            ANIME_BROWSER + "https://github.com/SkyD666/Imomoe/tree/master/doc/customdatasource/README.md",
                            "请在设置页面选择自定义数据源ads包,以便使用APP",
                            ImageBean("", "", ""),
                            "具体使用方法请点击此处",
                            null
                        )
                    )
                ),

                // 如何导入并使用自定义数据源？
                Header1Bean(
                    "",
                    "如何导入并使用自定义数据源？"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "找到ads数据源文件",
                    ImageBean("", R.drawable.ic_use_data_source_step_ads_files.toString(), ""),
                    "步骤1"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "用樱花动漫打开ads文件",
                    ImageBean("", R.drawable.ic_use_data_source_step_open_ads.toString(), ""),
                    "步骤2"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "确认导入数据源",
                    ImageBean(
                        "",
                        R.drawable.ic_use_data_source_step_import_dialog.toString(),
                        ""
                    ),
                    "步骤3"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击已有的数据,选择重启APP",
                    ImageBean("", R.drawable.ic_use_data_source_step_set.toString(), ""),
                    "步骤4"
                ),

                // 如何删除自定义数据源？
                Header1Bean(
                    "",
                    "如何删除自定义数据源？"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "长按要删除的项目",
                    ImageBean("", R.drawable.ic_use_data_source_step_long_click.toString(), ""),
                    "步骤1"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击确定,以删除",
                    ImageBean("", R.drawable.ic_use_data_source_step_delete.toString(), ""),
                    "步骤2"
                ),

                // 如何恢复默认数据源？
                Header1Bean(
                    "",
                    "如何恢复默认数据源？"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击右上角恢复按钮",
                    ImageBean(
                        "",
                        R.drawable.ic_use_data_source_step_reset_button.toString(),
                        ""
                    ),
                    "步骤1"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击重启,以恢复",
                    ImageBean(
                        "",
                        R.drawable.ic_use_data_source_step_reset_dialog.toString(),
                        ""
                    ),
                    "步骤2"
                ),

                // 如何进入自定义数据源界面？
                Header1Bean(
                    "",
                    "如何进入自定义数据源界面？"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击更多",
                    ImageBean("", R.drawable.ic_use_data_source_step_more.toString(), ""),
                    "步骤1"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击设置",
                    ImageBean("", R.drawable.ic_use_data_source_step_setting.toString(), ""),
                    "步骤2"
                ),
                AnimeCover1Bean(
                    "",
                    "",
                    "点击自定义数据源",
                    ImageBean("", R.drawable.ic_use_data_source_step_custom.toString(), ""),
                    "步骤3"
                )
            ), null
        )
    }
}