package com.walker.flexiblecore.ui.summary

import android.arch.lifecycle.ViewModel
import com.walker.flexiblecore.data.model.Summary
import com.walker.flexiblecore.data.repository.SummaryRepository

class SummaryViewModel(private val repository: SummaryRepository) : ViewModel() {

    var dataList = ArrayList<Summary>()

    fun listSummary() = repository.listSummary()
}
