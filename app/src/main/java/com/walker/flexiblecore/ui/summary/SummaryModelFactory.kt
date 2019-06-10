package com.walker.flexiblecore.ui.summary

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.walker.flexiblecore.data.repository.SummaryRepository

class SummaryModelFactory(private val repository: SummaryRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SummaryViewModel(repository) as T
    }
}