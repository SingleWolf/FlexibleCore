package com.walker.flexiblecore.ui.summary

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.walker.core.base.BaseActivityFragment
import com.walker.core.delegate.OnRecyclerItemClickListener
import com.walker.core.exception.OOMHelper
import com.walker.core.util.ToastUtils
import com.walker.flexiblecore.R
import com.walker.flexiblecore.data.Resource
import com.walker.flexiblecore.ui.OOMFragment
import com.walker.flexiblecore.util.InjectorUtil

class SummaryFragment : BaseActivityFragment() {
    private lateinit var rvSummary: RecyclerView
    private lateinit var viewModel: SummaryViewModel
    private lateinit var adapter: SummaryAdapter

    override fun buildView(baseView: View, savedInstanceState: Bundle?) {
        rvSummary = baseView.findViewById<RecyclerView>(R.id.rvSummary)
        viewModel = ViewModelProviders.of(this, InjectorUtil.getSummaryModelFactory()).get(SummaryViewModel::class.java)
        adapter = SummaryAdapter(viewModel.dataList)
        rvSummary?.let {
            it.layoutManager=LinearLayoutManager(holdActivity)
            it.adapter = adapter
            it.addOnItemTouchListener(object : OnRecyclerItemClickListener(it) {
                override fun onItemClick(vh: RecyclerView.ViewHolder) {
                    execItemClick(vh.layoutPosition)
                }

                override fun onItemLongClick(vh: RecyclerView.ViewHolder) {

                }
            })
        }
    }

    private fun execItemClick(pos: Int) {
        val summary = viewModel.dataList[pos]
        summary?.let {
            if (TextUtils.equals("OOM相关", it.title)) {
                addFragment(OOMFragment.instance, "OOMFragment")
            } else {
                ToastUtils.showShort(it.title)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_summary
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel.dataList.isEmpty()) {
            listSummary()
        }
    }

    private fun listSummary() {
        viewModel.listSummary().observe(this, Observer { listResource ->
            listResource ?: return@Observer

            if (listResource.status == Resource.SUCCESS) {
                viewModel.dataList.clear()
                viewModel.dataList.addAll(listResource.data!!)
                adapter.notifyDataSetChanged()
            } else if (listResource.status == Resource.ERROR) {
                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
            }
        })

        //故意引发异常
        //var nullList: Array<String>? = arrayOf()
        //Log.i("nullList", nullList!![3])
    }

    companion object {
        val instance: Fragment
            get() = SummaryFragment()
    }
}