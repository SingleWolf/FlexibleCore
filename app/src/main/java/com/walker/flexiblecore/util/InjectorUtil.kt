package com.walker.flexiblecore.util

import com.walker.flexiblecore.data.repository.SummaryRepository
import com.walker.flexiblecore.ui.summary.SummaryModelFactory

object InjectorUtil {

    @JvmStatic
    fun getSummaryModelFactory() = SummaryModelFactory(SummaryRepository.getInstance())
}