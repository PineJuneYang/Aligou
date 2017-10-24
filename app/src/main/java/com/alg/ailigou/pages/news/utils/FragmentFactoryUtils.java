package com.alg.ailigou.pages.news.utils;

import android.util.SparseArray;

import com.alg.ailigou.library.base.fragment.BaseFragment;
import com.alg.ailigou.pages.news.fragments.algmoving.NewsAlgMovingFragment;
import com.alg.ailigou.pages.news.fragments.hotnews.HotNewFragment;
import com.alg.ailigou.pages.news.fragments.industry.IndustryMovingFragment;
import com.alg.ailigou.pages.news.fragments.newrelease.NewRealeseFragment;

/**
 * Created by admin on 2016/12/21.
 */

public class FragmentFactoryUtils {

    private FragmentFactoryUtils() {
    }

    private static SparseArray<BaseFragment> mFragments = new SparseArray<BaseFragment>();

    //String[] newsTitles = {"爱利购动态", "行业动态", "新品发布", "热门资讯"};
    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    //爱利购动态
                    fragment = new NewsAlgMovingFragment();
                    break;
                case 1:
                    //行业动态
                    fragment = new IndustryMovingFragment();
                    break;
                case 2:
                    //新品发布
                    fragment = new NewRealeseFragment();
                    break;
                case 3:
                    //热门资讯
                    fragment = new HotNewFragment();
                    break;
            }
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;
    }
}

