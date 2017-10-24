package com.alg.ailigou.pages.personal.myorder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alg.ailigou.pages.personal.mycollection.fragment.MyCollectionFragment;
import com.alg.ailigou.pages.personal.myorder.fragment.MyOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 我的订单的fragment适配器
 */

public class MyOrderFragmentAdapter extends FragmentStatePagerAdapter{

    private List<String> tabLayoutTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    public MyOrderFragmentAdapter(FragmentManager fm, List<String> tabTitles) {
        super(fm);
        this.tabLayoutTitles = tabTitles;
        for (int i =0;i<tabTitles.size();i++){
            fragments.add(null);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size()!=tabLayoutTitles.size()||fragments.get(position)==null){
            fragments.add(position, MyOrderFragment.newInstance(position));
        }
        return fragments.get(position);
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
