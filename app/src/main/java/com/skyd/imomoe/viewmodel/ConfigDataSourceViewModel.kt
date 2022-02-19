package com.skyd.imomoe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skyd.imomoe.bean.DataSourceFileBean
import com.skyd.imomoe.ext.request
import com.skyd.imomoe.model.DataSourceManager
import com.skyd.imomoe.net.RetrofitManager
import com.skyd.imomoe.util.Util
import java.io.File


class ConfigDataSourceViewModel : ViewModel() {
    var mldDataSourceList: MutableLiveData<List<Any>?> = MutableLiveData()

    fun resetDataSource() = setDataSource(DataSourceManager.DEFAULT_DATA_SOURCE)

    fun clearDataSourceCache() {
        DataSourceManager.clearCache()
        RetrofitManager.setInstanceNull()
    }

    fun setDataSource(name: String) {
        DataSourceManager.dataSourceName = name
        DataSourceManager.clearCache()
        RetrofitManager.setInstanceNull()
        Util.restartApp()
    }

    fun deleteDataSource(bean: DataSourceFileBean) {
        bean.file.delete()
        getDataSourceList()
    }

    fun getDataSourceList(directoryPath: String = DataSourceManager.getJarDirectory()) {
        request(request = {
            val directory = File(directoryPath)
            if (!directory.isDirectory) {
                mldDataSourceList.postValue(ArrayList())
            } else {
                val jarList = directory.listFiles { _, name ->
                    name.endsWith(".ads", true) ||
                            name.endsWith(".jar", true)
                }
                mldDataSourceList.postValue((jarList ?: emptyArray())
                    .map {
                        DataSourceFileBean(
                            "", it, it.name == DataSourceManager.dataSourceName
                        )
                    }.toList()
                )
            }
        }, error = { mldDataSourceList.postValue(null) })
    }
}