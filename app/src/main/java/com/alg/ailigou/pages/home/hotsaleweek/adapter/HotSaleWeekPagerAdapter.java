package com.alg.ailigou.pages.home.hotsaleweek.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.alg.ailigou.pages.home.hotsaleweek.fragment.HotSaleWeekFragment;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于
 */

public class HotSaleWeekPagerAdapter extends FragmentPagerAdapter {
    List<String> titles;

    public HotSaleWeekPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return HotSaleWeekFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
