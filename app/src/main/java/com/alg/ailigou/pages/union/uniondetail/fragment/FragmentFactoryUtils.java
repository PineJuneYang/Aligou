package com.alg.ailigou.pages.union.uniondetail.fragment;

import android.util.SparseArray;

import com.alg.ailigou.library.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/12/21.
 */

public class FragmentFactoryUtils {

    private FragmentFactoryUtils() {
    }

    private static SparseArray<BaseFragment> mFragments = new SparseArray<BaseFragment>();


    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new UnionInfoFragment();
                    break;
                case 1:
                    fragment = new UnionShowFragment();
                    break;
                case 2:
                    fragment = new UnionMapFragment();
                    break;
            }
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;
    }
}

