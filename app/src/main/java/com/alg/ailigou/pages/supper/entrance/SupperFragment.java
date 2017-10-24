package com.alg.ailigou.pages.supper.entrance;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.supper.inject.DaggerSupperComponent;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.news.entrance
 * Created by Chris Chen on 2017/7/4 17:31.
 * Explain:
 */

public class SupperFragment extends BaseMvpFragment implements SupperContracts.View {

    @Inject
    SupperPresenter presenter;

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_supper;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerSupperComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }
}
