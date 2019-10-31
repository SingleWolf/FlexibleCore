package com.walker.flexiblecore.ui

import android.os.Bundle
import android.util.Log
import com.walker.core.base.BaseFragmentActivity
import com.walker.flexiblecore.R
import com.walker.flexiblecore.ui.summary.SummaryFragment

class MainActivity : BaseFragmentActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun getFragmentContentId(): Int {
        return R.id.fragContent
    }

    override fun init(savedInstanceState: Bundle?) {
        addFragment(SummaryFragment.instance, "SummaryFragment")

        aspectTest()
    }

    private fun aspectTest() {
        test1()
        test2()
    }

    private fun test2() {
        test("test2")
    }

    private fun test1() {
        test("test1")
    }

    private fun test(value: String) {
        Log.i("test", value)
    }

}