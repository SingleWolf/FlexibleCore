package com.walker.flexiblecore.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.walker.flexiblecore.data.Resource
import com.walker.flexiblecore.data.model.Summary
import com.walker.flexiblecore.util.FlexibleExecutors

class SummaryRepository private constructor() {

    fun listSummary(): LiveData<Resource<List<Summary>>> {
        val liveData = MutableLiveData<Resource<List<Summary>>>()
        liveData.value = Resource.loading(null)

        FlexibleExecutors.diskIO.execute {
            val list = mutableListOf<Summary>()
            Summary().let {
                it.title = "产品详情页"
                list.add(it)
            }
            Summary().let {
                it.title = "选取图片"
                list.add(it)
            }

            Summary().let {
                it.title = "OOM相关"
                list.add(it)
            }

            Summary().let {
                it.title = "算法测试"
                list.add(it)
            }
            liveData.postValue(Resource.success(list))
        }

        return liveData
    }

    companion object {

        private var instance: SummaryRepository? = null

        fun getInstance(): SummaryRepository {
            if (instance == null) {
                synchronized(SummaryRepository::class.java) {
                    if (instance == null) {
                        instance = SummaryRepository()
                    }
                }
            }
            return instance!!
        }
    }
}