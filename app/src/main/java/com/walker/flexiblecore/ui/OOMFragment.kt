package com.walker.flexiblecore.ui

import android.os.Bundle
import android.os.Process
import android.support.v4.app.Fragment
import android.view.View
import com.walker.core.base.BaseActivityFragment
import com.walker.core.exception.OOMHelper
import com.walker.flexiblecore.R
import kotlinx.android.synthetic.main.fragment_oom.*

/**
 *@Author Walker
 *
 *@Date   2019-10-31 15:26
 *
 *@Summary OOM观察页面
 */
class OOMFragment : BaseActivityFragment(), View.OnClickListener {
    private var digtal = -1
    override fun buildView(baseView: View, savedInstanceState: Bundle?) {
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_oom

    override fun onClick(v: View?) {
        val value=et_digtal.text.toString().trim()
        digtal = if(value.isEmpty()){
            500
        }else{
            Integer.valueOf(value)
        }
        when (v?.id) {
            R.id.bt1 -> tv_dashboard.text = OOMHelper.get().listStatisticsInfo(Process.myPid())
            R.id.bt2 -> tv_dashboard.text = OOMHelper.get().listStatisticsSummary(Process.myPid())
            R.id.bt3 -> tv_dashboard.text = OOMHelper.get().memoryInfo
            R.id.bt4 -> OOMHelper.get().testIncreaseFD(digtal)
            R.id.bt5 -> OOMHelper.get().testIncreaseThread(digtal)
            R.id.bt6 -> OOMHelper.get().testAllocateJavaHeap(digtal)
            R.id.bt7 -> OOMHelper.get().testGCAndDeallocate()
        }
    }

    companion object {
        val instance: Fragment
            get() = OOMFragment()
    }
}