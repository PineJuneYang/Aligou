package com.alg.ailigou.common.inject;

import android.content.Context;

import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.library.base.fragment.BaseFragment;
import com.alg.ailigou.pages.home.consts.HomeConsts;

import dagger.Module;
import dagger.Provides;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.inject
 * Created by Chris Chen on 2017/7/3 15:15.
 * Explain:基本的Fragment的注入module
 */
@Module
public class FragmentModule implements HomeConsts{
    private BaseFragment baseFragment;
    private BaseActivity baseActivity;

    /**
     * 构造方法
     *
     * @param baseFragment
     */
    public FragmentModule(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    public FragmentModule(BaseActivity baseActivity){
        this.baseActivity = baseActivity;
    }


    @Provides
    Context proContext(){
        return baseFragment.getContext();
    }


}
