package com.alg.ailigou.pages.personal.inject;

import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.library.base.fragment.BaseFragment;

import dagger.Module;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于
 */
@Module
public class PersonalModule {
    private BaseActivity baseActivity;
    private BaseFragment baseFragment;

    public PersonalModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public PersonalModule(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }
}
