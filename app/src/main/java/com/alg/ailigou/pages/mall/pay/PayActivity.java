package com.alg.ailigou.pages.mall.pay;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.pay
 * Created by Chris Chen on 2017/7/21 16:01.
 * Explain:支付
 */

public class PayActivity extends BaseMvpActivity implements PayContracts.View {
    @Inject
    PayPresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_pay;
    }
}
