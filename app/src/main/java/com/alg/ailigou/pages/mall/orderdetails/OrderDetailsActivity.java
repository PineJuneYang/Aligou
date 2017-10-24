package com.alg.ailigou.pages.mall.orderdetails;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.alg.ailigou.pages.mall.orderdetails.adapter.OrderDtailsAdpter;
import com.alg.ailigou.pages.personal.receiveaddress.ReceiveAddressActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/1.
 * 此类或接口用于 确认订单,订单详情
 */

public class OrderDetailsActivity extends BaseMvpActivity implements OrderDetailsContracts.View {
    @Inject
    OrderDetailsPresenter mPresenter;
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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_telnumber)
    TextView mTvTelnumber;
    @BindView(R.id.rl_userinfo)
    RelativeLayout mRlUserinfo;

    @BindView(R.id.tv_ship_money)
    TextView mTvShipMoney;
    @BindView(R.id.tv_ligou_absolve_money)
    TextView mTvLigouAbsolveMoney;
    @BindView(R.id.ll_ligou_absolve_money)
    LinearLayout mLlLigouAbsolveMoney;
    @BindView(R.id.tv_pay_money_number)
    TextView mTvPayMoneyNumber;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_zhifubao)
    LinearLayout mLlZhifubao;
    @BindView(R.id.ll_wechat)
    LinearLayout mLlWechat;
    @BindView(R.id.ll_union_pay)
    LinearLayout mLlUnionPay;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    private long orderId;
    private ShippingAddressModel mAddressModel;
    private List<CommodityModel> mCommodityModels;
    private OrderDtailsAdpter mAdpter;
    private OrderDetailsDataModel dataModel;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_order_details;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected void initBase() {
        super.initBase();
        mCommodityModels = new ArrayList<>();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("确认订单");
        initRecylerView();

        // TODO: 2017/8/1  
        orderId = 1;
        mPresenter.loadData(orderId);

    }

    /**
     * RecylerView设置
     */
    private void initRecylerView() {
        mAdpter = new OrderDtailsAdpter(mCommodityModels, this);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        mRecylerView.setLayoutManager(mLinearLayoutManager);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setNestedScrollingEnabled(false);
        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) mRecylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecylerView.setAdapter(mAdpter);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        //这个是加减号的点击监听
        mAdpter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_cart_delete:
                        if (mCommodityModels.get(position).buyCount > 1) {
                            mCommodityModels.get(position).buyCount--;
                            mTvPayMoneyNumber.setText("合计:  " + countPrice());
                            mAdpter.notifyItemChanged(position);
                        }
                        break;
                    case R.id.tv_cart_add:
                        mCommodityModels.get(position).buyCount++;
                        mTvPayMoneyNumber.setText("合计:  " + countPrice());
                        mAdpter.notifyItemChanged(position);
                        break;
                }
            }
        });
    }

    /**
     * 计算价格
     *
     * @return
     */
    private double countPrice() {
        double allPrice = 0;
        for (int i = 0; i < dataModel.goods.size(); i++) {
            CommodityModel goods = dataModel.goods.get(i);
            allPrice = allPrice + (goods.buyCount *Double.parseDouble( goods.price));
        }
        return allPrice;
    }

    @Override
    public void updataViews(OrderDetailsDataModel dataModel) {
        this.dataModel = dataModel;
        if (dataModel != null) {
            mAddressModel = dataModel.mAddressModel;
            mTvPayMoneyNumber.setText("" + dataModel.allPrice);
            mTvShipMoney.setText(dataModel.expressFee + "元");//运费
            mTvName.setText(mAddressModel.name);
            mTvTelnumber.setText(mAddressModel.telNumber);
            mTvAddress.setText(mAddressModel.address);
            mCommodityModels.addAll(dataModel.goods);
            mAdpter.notifyDataSetChanged();
        }
    }


    @OnClick({R.id.ll_base_back, R.id.rl_userinfo, R.id.ll_ligou_absolve_money, R.id.ll_zhifubao, R.id.ll_wechat, R.id.ll_union_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.rl_userinfo://收货地址
                startActivity(ReceiveAddressActivity.class);
                break;
            case R.id.ll_ligou_absolve_money://使用利购券抵扣

                break;
            case R.id.ll_zhifubao:
                break;
            case R.id.ll_wechat:
                break;
            case R.id.ll_union_pay:
                break;
        }
    }


}
