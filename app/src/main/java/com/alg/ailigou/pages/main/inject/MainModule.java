package com.alg.ailigou.pages.main.inject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.pages.cart.entrance.AiligouCartFragment;
import com.alg.ailigou.pages.cart.entrance.CartFragment;
import com.alg.ailigou.pages.classification.entrance.ClassificationFragment;
import com.alg.ailigou.pages.home.entrance.HomeFragment;
import com.alg.ailigou.pages.personal.entrance.PersonalFragment;
import com.alg.ailigou.pages.union.entrance.UnionFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.main.inject
 * Created by Chris Chen on 2017/7/4 15:37.
 * Explain:
 */
@Module
public class MainModule {
    private BaseActivity baseActivity;

    public MainModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    List<Fragment> proFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassificationFragment());
        fragmentList.add(new UnionFragment());
        fragmentList.add(new AiligouCartFragment());
        fragmentList.add(new PersonalFragment());

        return fragmentList;
    }

    @Provides
    FragmentManager proFragmentManager() {
        return baseActivity.getSupportFragmentManager();
    }
}
