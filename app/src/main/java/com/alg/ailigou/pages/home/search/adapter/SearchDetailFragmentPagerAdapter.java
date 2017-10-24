package com.alg.ailigou.pages.home.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alg.ailigou.pages.home.hotsalelist.fragment.HotSaleListFragment;
import com.alg.ailigou.pages.home.search.fragment.SearchDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/26
 * 此类或接口用于
 */

public class SearchDetailFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<String> tabLayoutTitles = new ArrayList<>();
    private  List<Fragment> fragments ;
    private String searchWords;

    public SearchDetailFragmentPagerAdapter(FragmentManager fm, List<String> tabTitles, List<Fragment> searchDetailFragments, String searchWords) {
        super(fm);
        this.tabLayoutTitles = tabTitles;
        this.fragments = searchDetailFragments;
        this.searchWords = searchWords;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments==null||fragments.size()!=tabLayoutTitles.size()){
            fragments.add(position,SearchDetailFragment.newInstance(searchWords,position));
            return SearchDetailFragment.newInstance(searchWords,position);
        }else {
           return fragments.get(position);
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
