package com.alg.ailigou.pages.main.entrance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.main.entrance.adapter
 * Created by Chris Chen on 2017/7/4 15:45.
 * Explain:
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    @Inject
    public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
