package com.alg.ailigou.pages.personal.paylist;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/11.
 * 此类或接口用于 支付选择列表界面
 */

public class PayListActivity extends BaseMvpActivity {
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
    @BindView(R.id.ll_ali_pay)
    LinearLayout mLlAliPay;
    @BindView(R.id.ll_weixin_pay)
    LinearLayout mLlWeixinPay;
    @BindView(R.id.tv_wait_pay_money_count)
    TextView mTvWaitPayMoneyCount;
    @BindView(R.id.tv_wait_pay_money)
    TextView mTvWaitPayMoney;
    @BindView(R.id.ll_wait_pay_money)
    LinearLayout mLlWaitPayMoney;


    private OrderDetailsDataModel mModel;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_pay_list;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("确认付款");
    }

    @OnClick({R.id.ll_base_back, R.id.ll_ali_pay, R.id.ll_weixin_pay, R.id.ll_wait_pay_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_ali_pay:
                break;
            case R.id.ll_weixin_pay:
                break;
            case R.id.ll_wait_pay_money:
                break;
        }
    }


    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }
}
