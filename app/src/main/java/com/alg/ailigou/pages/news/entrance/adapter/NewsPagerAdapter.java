package com.alg.ailigou.pages.news.entrance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.alg.ailigou.pages.news.utils.FragmentFactoryUtils;

/**
 * Created by 海航
 * on 2017/7/13.
 * 此类或接口用于
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public NewsPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactoryUtils.createFragment(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
