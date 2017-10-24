package com.alg.ailigou.pages.home.hotsalelist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alg.ailigou.pages.home.hotsalelist.fragment.HotSaleListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 热销榜的fragment适配器
 */

public class HotSaleListFragmentAdapter extends FragmentPagerAdapter{

    private List<String> tabLayoutTitles = new ArrayList<>();
    public HotSaleListFragmentAdapter(FragmentManager fm, List<String> tabTitles) {
        super(fm);
        this.tabLayoutTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return HotSaleListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return tabLayoutTitles == null?0:tabLayoutTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabLayoutTitles.get(position);
    }
}
