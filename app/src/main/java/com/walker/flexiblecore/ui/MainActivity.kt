package com.walker.flexiblecore.ui

import android.os.Bundle
import com.walker.core.base.BaseFragmentActivity
import com.walker.flexiblecore.R
import com.walker.flexiblecore.ui.summary.SummaryFragment

class MainActivity:BaseFragmentActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun getFragmentContentId(): Int {
        return R.id.fragContent
    }

    override fun init(savedInstanceState: Bundle?) {
        addFragment(SummaryFragment.instance, "SummaryFragment")
    }

}