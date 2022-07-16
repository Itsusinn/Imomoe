package com.skyd.imomoe.view.adapter.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import java.lang.reflect.ParameterizedType

class LazyGridAdapter(
    private var proxyList: MutableList<Proxy<*>> = mutableListOf(),
) {
    @Suppress("UNCHECKED_CAST")
    @Composable
    fun draw(index: Int, data: Any) {
        var type = -1
        type = getProxyIndex(data)
        if (type != -1) (proxyList[type] as Proxy<Any>).draw(index, data)
    }

    // 获取策略在列表中的索引，可能返回-1
    private fun getProxyIndex(data: Any): Int = proxyList.indexOfFirst {
        // 如果Proxy<T>中的第一个类型参数T和数据的类型相同，则返回对应策略的索引
        (it.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].let { argument ->
            if (argument.toString() == data.javaClass.toString())
                true    // 正常情况
            else {
                // Proxy第一个泛型是类似List<T>，又嵌套了个泛型
                if (argument is ParameterizedType)
                    argument.rawType.toString() == data.javaClass.toString()
                else false
            }
        }
    }

    // 抽象策略类
    abstract class Proxy<T> {
        @Composable
        abstract fun draw(index: Int, data: T)
    }
}