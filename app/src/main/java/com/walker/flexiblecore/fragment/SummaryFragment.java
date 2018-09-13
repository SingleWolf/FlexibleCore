package com.walker.flexiblecore.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walker.core.base.BaseActivityFragment;
import com.walker.flexiblecore.R;
import com.walker.flexiblecore.adapter.SummaryAdapter;
import com.walker.flexiblecore.bean.Summary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Walker
 * @date on 2018/7/10 0010 上午 10:42
 * @email feitianwumu@163.com
 * @desc 概要
 */
public class SummaryFragment extends BaseActivityFragment {
    @BindView(R.id.rvSummary)
    RecyclerView rvSummary;
    Unbinder unbinder;

    private List<Summary> mSummaryList;

    public static Fragment getInstance() {
        Fragment fragment=new SummaryFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initData();
    }

    private void initData() {
        mSummaryList = new ArrayList<>();
        Summary scrollDetail = new Summary();
        scrollDetail.setTitle("产品详情页");
        mSummaryList.add(scrollDetail);
    }

    @Override
    protected void buildView(View baseView, Bundle savedInstanceState) {
        rvSummary.setLayoutManager(new LinearLayoutManager(getHoldActivity()));
        SummaryAdapter adapter = new SummaryAdapter(mSummaryList);
        rvSummary.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_summary;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
