package com.walker.flexiblecore;

import android.os.Bundle;

import com.walker.core.base.BaseFragmentActivity;
import com.walker.flexiblecore.fragment.SummaryFragment;


public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        addFragment(SummaryFragment.getInstance(), "SummaryFragment");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragContent;
    }

}
