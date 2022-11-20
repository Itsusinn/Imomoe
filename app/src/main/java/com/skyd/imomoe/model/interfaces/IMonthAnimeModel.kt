package com.skyd.imomoe.model.interfaces

import com.skyd.imomoe.bean.PageNumberBean

/**
 * 获取季度番剧数据的接口
 */
interface IMonthAnimeModel : IBase {
    /**
     * @param partUrl  页面部分url，不为null
     * @return Pair，不可为null
     * ArrayList<Any>：季度番剧数据ArrayList，不为null；
     * PageNumberBean：下一页数据地址，可为null，为空则没有下一页
     */
    suspend fun getMonthAnimeData(partUrl: String): Pair<ArrayList<Any>, PageNumberBean?>

    companion object {
        const val implName = "MonthAnimeModel"
    }
}