package com.alg.ailigou.pages.personal.selectrefundmethod;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/17.
 * 此类或接口用于  退款选择  退款方式
 */

public class SelectRefundsMethodActivity extends BaseMvpActivity {
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.rl_only_return_money)
    RelativeLayout mRlOnlyReturnMoney;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.rl_return_money_and_goods)
    RelativeLayout mRlReturnMoneyAndGoods;


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_select_refund_method;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("退款方式");
    }



    @OnClick({R.id.ll_base_back, R.id.rl_only_return_money, R.id.rl_return_money_and_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.rl_only_return_money:

                break;
            case R.id.rl_return_money_and_goods:

                break;
        }
    }
}
