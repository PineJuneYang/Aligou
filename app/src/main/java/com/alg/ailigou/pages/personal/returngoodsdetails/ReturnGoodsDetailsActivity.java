package com.alg.ailigou.pages.personal.returngoodsdetails;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/17.
 * 此类或接口用于 退款详情
 */

public class ReturnGoodsDetailsActivity extends BaseMvpActivity {
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
    @BindView(R.id.tv_return_goods_number)
    TextView mTvReturnGoodsNumber;
    @BindView(R.id.tv_apply_time)
    TextView mTvApplyTime;
    @BindView(R.id.tv_return_goods_reason)
    TextView mTvReturnGoodsReason;
    @BindView(R.id.tv_return_goods_desc)
    TextView mTvReturnGoodsDesc;
    @BindView(R.id.tv_return_goods_type)
    TextView mTvReturnGoodsType;
    @BindView(R.id.tv_return_goods_time)
    TextView mTvReturnGoodsTime;
    @BindView(R.id.tv_return_goods_money)
    TextView mTvReturnGoodsMoney;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_return_goods_details;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
    }

    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }


}
