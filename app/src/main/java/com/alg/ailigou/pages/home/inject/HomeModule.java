package com.alg.ailigou.pages.home.inject;

import android.content.Context;

import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.library.base.fragment.BaseFragment;
import com.alg.ailigou.pages.home.consts.HomeConsts;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.inject
 * Created by Chris Chen on 2017/7/6 12:07.
 * Explain:
 */
@Module
public class HomeModule implements HomeConsts {
    private BaseActivity baseActivity;
    private BaseFragment baseFragment;

    public HomeModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public HomeModule(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }


    @Provides
    Context proContext() {
        return baseActivity == null ? baseFragment.getBaseContext() : baseActivity.getBaseContext();
    }

    @Provides
    List<FunctionModel> proFunctionList() {
        List<FunctionModel> functionModelList = new ArrayList<>();
        for (int i = 0; i < HOME_FUNCTION_TITLES.length; i++) {
            functionModelList.add(new FunctionModel(i + 1, HOME_FUNCTION_TITLES[i], HOME_FENCTION_ICONS[i]));
        }
        return functionModelList;
    }


}
