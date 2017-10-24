package com.alg.ailigou.pages.mall.goodslist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alg.ailigou.pages.home.hotsalelist.fragment.HotSaleListFragment;
import com.alg.ailigou.pages.mall.goodslist.fragment.GoodsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 热销榜的fragment适配器
 */

public class GoodsListFragmentAdapter extends FragmentPagerAdapter{

    private List<String> tabLayoutTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    public GoodsListFragmentAdapter(FragmentManager fm, List<String> tabTitles) {
        super(fm);
        this.tabLayoutTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments.size()!=tabLayoutTitles.size()||fragments.get(position)==null){
            fragments.add(com.alg.ailigou.pages.home.goodslist.fragment.GoodsListFragment.newInstance(position));
            return com.alg.ailigou.pages.home.goodslist.fragment.GoodsListFragment.newInstance(position);
        }else {
            return  fragments.get(position);
        }
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
