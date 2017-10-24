package com.alg.ailigou.pages.personal.mybill;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.MyBillData;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.moneychange.MoneyChangeActivity;
import com.alg.ailigou.pages.personal.offlineorder.OffLineOrderActivity;
import com.alg.ailigou.pages.personal.onlineorder.OnLineOrderActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/14.
 * 此类或接口用于 我的账单
 */

public class MyBillActivity extends BaseMvpActivity {
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
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.tv_7)
    TextView mTv7;
    @BindView(R.id.tv_8)
    TextView mTv8;
    @BindView(R.id.ll_lable_1)
    LinearLayout mLlLable1;
    @BindView(R.id.ll_lable_2)
    LinearLayout mLlLable2;
    @BindView(R.id.ll_lable_3)
    LinearLayout mLlLable3;
    @BindView(R.id.ll_lable_4)
    LinearLayout mLlLable4;
    @BindView(R.id.rl_base_title)
    RelativeLayout mRlBaseTitle;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_bill;
    }

    @Override
    protected void initViewAndListener() {
        mTvBaseTitle.setText("我的账单");
        loadData();
        mRlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        mTvBaseTitle.setTextColor(Color.BLACK);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }


    @OnClick({R.id.ll_base_back, R.id.ll_lable_1, R.id.ll_lable_2, R.id.ll_lable_3, R.id.ll_lable_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_lable_1:
                startActivity(MoneyChangeActivity.class);
                break;
            case R.id.ll_lable_2:

                break;
            case R.id.ll_lable_3:
                startActivity(OnLineOrderActivity.class);
                break;
            case R.id.ll_lable_4:
                startActivity(OffLineOrderActivity.class);
                break;
        }
    }

    //    double totalConsume;//累计消费总额
//     double accountOverage;//账户余额
//     double successChangeMoney;//成功提取金额
//     double totalGiftPaper;//累计赠送利购券
//     double nowRights;//当前拥有赠送权
//     double totalGetRights;//历史累计赠送权
//     double totalFreezeMoney;//冻结金额
//     double liPoints;//利积分
    public void loadData() {
        UserApi.getMyBill(new NetCallback<MyBillData>() {
            @Override
            protected void onComplete(NetResponse<MyBillData> netResponse) {
                if (netResponse.isSuccess) {
                    MyBillData data = netResponse.data;
                    mTv1.setText("累计消费总额:" + data.totalConsume + "元");
                    mTv2.setText("账户余额:" + data.accountOverage + "元");
                    mTv3.setText("成功提现金额:" + data.successChangeMoney + "元");
                    mTv4.setText("累计赠送利购券:" + data.totalGiftPaper + "元");
                    mTv5.setText("当前拥有赠送权:" + data.nowRights + "个");
                    mTv6.setText("历史累计赠送权:" + data.totalGetRights + "个");
                    mTv7.setText("冻结金额:" + data.totalFreezeMoney + "元");
                    mTv8.setText("利积分余额:" + data.liPoints + "元");
                }
            }
        });
    }


}
