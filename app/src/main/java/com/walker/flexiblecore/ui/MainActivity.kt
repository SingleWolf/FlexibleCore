package com.walker.flexiblecore.ui

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.walker.core.base.BaseFragmentActivity
import com.walker.core.util.ToastUtils
import com.walker.flexiblecore.R
import com.walker.flexiblecore.ui.summary.SummaryFragment
import com.walker.optimize.analyzer.IndexAnalyzer
import com.walker.optimize.analyzer.TimeAnalyzerMgr

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

        val keyInfo = IndexAnalyzer().listKeyInfo(applicationContext)
        Log.i("KeyInfo", keyInfo)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TimeAnalyzerMgr.getInstance().getTimeAnalyzer(1).recordingTimeTag("MainActivity-onCreate-start")
        super.onCreate(savedInstanceState)
        TimeAnalyzerMgr.getInstance().getTimeAnalyzer(1).end("MainActivity-onCreate-over",true){
            ToastUtils.showCenterShort(it?:it)
        }
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