package com.alg.ailigou.pages.mall.brand;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.brand.holder
 * Created by Chris Chen on 2017/7/21 15:54.
 * Explain:某品牌
 */

public class BrandActivity extends BaseMvpActivity implements BrandContracts.View {
    @Inject
    BrandPresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_brand;
    }
}
