package com.alg.ailigou.pages.personal.commonorderdetails;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.widget.CommonDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.seelogistics.SeeLogisticsActivity;
import com.alg.ailigou.pages.personal.seelogistics.adapter.OrderGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于 这个是我的订单界面的订单详情界面  和mall 里面界面和逻辑处理不同 所以重新写一个
 */

public class CommonOrderDetailsActivity extends BaseMvpActivity implements CommonOrderDetailsContracts.View {
    @Inject
    CommonOrderDetailsPresenter mPresenter;

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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_telnumber)
    TextView mTvTelnumber;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rl_userinfo)
    RelativeLayout mRlUserinfo;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.tv_ship_money)
    TextView mTvShipMoney;
    @BindView(R.id.tv_ligou_absolve_money)
    TextView mTvLigouAbsolveMoney;
    @BindView(R.id.ll_ligou_absolve_money)
    LinearLayout mLlLigouAbsolveMoney;
    @BindView(R.id.tv_pay_money)
    TextView mTvPayMoney;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_copy_number)
    TextView mTvCopyNumber;
    @BindView(R.id.ll_logistics)
    LinearLayout mLlLogistics;
    @BindView(R.id.tv_wait_pay_money_count)
    TextView mTvWaitPayMoneyCount;
    @BindView(R.id.tv_wait_pay_money)
    TextView mTvWaitPayMoney;
    @BindView(R.id.ll_wait_pay_money)
    LinearLayout mLlWaitPayMoney;
    @BindView(R.id.tv_sure_get_goods)
    TextView mTvSureGetGoods;
    @BindView(R.id.ll_sure_get_goods)
    LinearLayout mLlSureGetGoods;
    @BindView(R.id.tv_receive_goods_time)
    TextView mTvReceiveGoodsTime;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.tv_goods_money)
    TextView mTvGoodsMoney;
    @BindView(R.id.ll_goods_money)
    LinearLayout mLlGoodsMoney;
    private int type;//进行区分的标记 是待发货 代收货还是什么其他的
    private int orderType;//进行区分是  利购券兑换的  还是正常购买的
    private long orderId;
    private OrderGoodsAdapter mAdpter;
    private OrderDetailsDataModel mDataModel;
    List<CommodityModel> goods = new ArrayList<>();
    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_common_order_details;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("订单详情");
        //设置删除线
        mTvGoodsMoney.setPaintFlags(mTvGoodsMoney.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mDataModel = (OrderDetailsDataModel) getIntent().getSerializableExtra("orderDetailsDataModel");
        if (mDataModel!=null){
            type = mDataModel.orderState;
            orderType = mDataModel.orderType;
            orderId = mDataModel.orderId;
        }
        initViewForType();
        initRecylerView();
        mPresenter.loadGoodDetails(orderId);
    }

    private void initRecylerView() {
        mAdpter = new OrderGoodsAdapter(goods, this,orderType);
        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) mRecylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecylerView.setAdapter(mAdpter);
    }

    @Override
    protected void initInjector() {
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    /**
     * 根据type  显示不同的布局
     */
    private void initViewForType() {
        switch (orderType) {
            case PersonalConsts.ORDER_TYPE_EXCHANGE:
                mLlGoodsMoney.setVisibility(View.VISIBLE);
                break;
            case PersonalConsts.ORDER_TYPE_BUY:
                mLlGoodsMoney.setVisibility(View.GONE);
                break;
        }
        switch (type) {
            case PersonalConsts.WAIT_PAR_MONEY://待付款
                mLlWaitPayMoney.setVisibility(View.VISIBLE);
                mTvBaseEdit.setVisibility(View.VISIBLE);
                mTvBaseEdit.setText("取消订单");
                break;
            case PersonalConsts.WAIT_RECEIVE_GOODS://待收货
                mLlSureGetGoods.setVisibility(View.VISIBLE);
                mTvBaseEdit.setVisibility(View.VISIBLE);
                mTvBaseEdit.setText("取消订单");
                break;
            case PersonalConsts.WAIT_SEND_GOODS://待发货
                mTvBaseEdit.setVisibility(View.VISIBLE);
                mTvBaseEdit.setText("退款");
                break;
            case PersonalConsts.WAIT_COMMENT://待评价
                mLlComment.setVisibility(View.VISIBLE);
                mTvReceiveGoodsTime.setVisibility(View.VISIBLE);
                break;
            case PersonalConsts.FINISH_ORDER://所有流程都做完的订单
                mTvReceiveGoodsTime.setVisibility(View.VISIBLE);
                break;

        }
    }

    @OnClick({R.id.ll_base_back, R.id.ll_logistics, R.id.tv_wait_pay_money, R.id.tv_sure_get_goods, R.id.ll_base_edit, R.id.ll_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_base_edit:
                showDialog();
                break;
            case R.id.ll_logistics://查看物流
                startActivity(SeeLogisticsActivity.class);
                break;
            case R.id.tv_wait_pay_money://去付款

                break;
            case R.id.tv_sure_get_goods://确认收货

                break;
            case R.id.ll_comment://去评价

                break;
        }
    }


    @Override
    public void setOrderData(OrderDetailsDataModel object) {

    }

    private CommonDialog dialog;

    @Override
    public void showDialog() {
        switch (type) {
            case PersonalConsts.WAIT_PAR_MONEY://待付款 (取消订单逻辑)
                dialog = new CommonDialog(this, "是否确定取消订单?");
                break;
            case PersonalConsts.WAIT_RECEIVE_GOODS://待收货 (退款逻辑)
                dialog = new CommonDialog(this, "是否确定取消订单?");
                break;
            case PersonalConsts.WAIT_SEND_GOODS://待发货 (退款逻辑)
                dialog = new CommonDialog(this, "是否确定取消订单?");
                break;

        }
        dialog.show();
        dialog.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
        dialog.getSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cancelOrder(orderId);
            }
        });
    }

    @Override
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
