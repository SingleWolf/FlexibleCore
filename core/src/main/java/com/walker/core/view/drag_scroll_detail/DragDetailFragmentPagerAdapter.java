package com.walker.core.view.drag_scroll_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @date on 2018/1/26 0026 上午 11:42
 * @author Walker
 * @email feitianwumu@163.com
 * @desc  DragScrollDetailsLayout指定的FragmentPagerAdapter
 */
public abstract class DragDetailFragmentPagerAdapter extends FragmentPagerAdapter {

    private View mCurrentView;

    public DragDetailFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            mCurrentView = (View) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            mCurrentView = fragment.getView();
        }
    }
    public View getPrimaryItem() {
        return mCurrentView;
    }
}