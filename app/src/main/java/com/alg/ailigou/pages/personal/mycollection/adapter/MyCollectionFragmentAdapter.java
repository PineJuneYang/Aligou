package com.alg.ailigou.pages.personal.mycollection.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alg.ailigou.pages.home.goodslist.fragment.GoodsListFragment;
import com.alg.ailigou.pages.personal.mycollection.fragment.MyCollectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 我的收藏的fragment适配器
 */

public class MyCollectionFragmentAdapter extends FragmentPagerAdapter{

    private List<String> tabLayoutTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    public MyCollectionFragmentAdapter(FragmentManager fm, List<String> tabTitles) {
        super(fm);
        this.tabLayoutTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size()!=tabLayoutTitles.size()||fragments.get(position)==null){
            fragments.add(position, MyCollectionFragment.newInstance(position));
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
