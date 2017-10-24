package com.alg.ailigou.pages.home.ligouchangedetails;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于  利购券兑换详情
 */

public class LigouChangeDetailsActivity extends BaseMvpActivity implements LigouChangeDetailsContracts.View {
    @Inject
    LigouChangeDetailsPresenter mPresenter;
    @BindView(R.id.tv_union_number)
    TextView mTvUnionNumber;
    @BindView(R.id.tv_union_name)
    TextView mTvUnionName;
    @BindView(R.id.tv_union_telnumber)
    TextView mTvUnionTelnumber;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_title)
    TextView mTvGoodsTitle;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.tv_ligou_count)
    TextView mTvLigouCount;
    @BindView(R.id.tv_goods_count)
    TextView mTvGoodsCount;
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_ligou_change_details;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("利购券兑换详情");
    }

    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void notify(Object titles) {

    }
}
