package com.alg.ailigou.pages.mall.advice;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.advice
 * Created by Chris Chen on 2017/7/21 16:06.
 * Explain:在线咨询
 */

public class AdviceActivity extends BaseMvpActivity implements AdviceContracts.View {
    @Inject
    AdvicePresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_advice;
    }
}
