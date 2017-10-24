package com.alg.ailigou.pages.news.inject;

import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.library.base.fragment.BaseFragment;

import dagger.Module;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于
 */
@Module
public class NewsMoudule {
    private BaseActivity baseActivity;
    private BaseFragment baseFragment;

    public NewsMoudule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public NewsMoudule(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

}
