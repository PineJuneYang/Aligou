package com.alg.ailigou.pages.union.uniondetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.alg.ailigou.pages.union.uniondetail.fragment.FragmentFactoryUtils;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UnionPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;

    public UnionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public UnionPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactoryUtils.createFragment(position);
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
