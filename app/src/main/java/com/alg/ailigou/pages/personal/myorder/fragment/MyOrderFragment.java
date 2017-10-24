package com.alg.ailigou.pages.personal.myorder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.personal.applyreturnmoney.ApplyReturnMoneyActivity;
import com.alg.ailigou.pages.personal.commentdetail.CommentDetailActivity;
import com.alg.ailigou.pages.personal.commonorderdetails.CommonOrderDetailsActivity;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.myorder.adapter.AllFragmentAdapter;
import com.alg.ailigou.pages.personal.myorder.adapter.PreCommentFragmentAdapter;
import com.alg.ailigou.pages.personal.myorder.adapter.PrePaymentFragmentAdapter;
import com.alg.ailigou.pages.personal.myorder.adapter.PreReceiveFragmentAdapter;
import com.alg.ailigou.pages.personal.myorder.adapter.PreSendFragmentAdapter;
import com.alg.ailigou.pages.personal.paylist.PayListActivity;
import com.alg.ailigou.pages.personal.seelogistics.SeeLogisticsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public class MyOrderFragment extends BaseMvpFragment implements MyOrderFragmentContracts.View, OnClickListener, OnItemClickListener {

    private static MyOrderFragment myOrderFragment;


    private List<OrderDetailsDataModel> commodityModels =new ArrayList<>();
    private List<OrderDetailsDataModel> prePaymentCommodityModels =new ArrayList<>();
    private List<OrderDetailsDataModel> preSendCommodityModels =new ArrayList<>();
    private List<OrderDetailsDataModel> preReceiveCommodityModels =new ArrayList<>();
    private List<OrderDetailsDataModel> preCommentCommodityModels =new ArrayList<>();
//    private List<OrderDetailsDataModel> preCommentCommodityModels =new ArrayList<>();


    @Inject
    MyOrderFragmentPresenter presenter;
    @BindView(R.id.recyler_view)
    RecyclerView recylerView;


    private int position;
    private AllFragmentAdapter allFragmentAdapter;
    private PrePaymentFragmentAdapter prePaymentFragmentAdapter;
    private PreSendFragmentAdapter preSendFragmentAdapter;
    private PreReceiveFragmentAdapter preReceiveFragmentAdapter;
    private PreCommentFragmentAdapter preCommentFragmentAdapter;
    private OrderDetailsDataModel orderDetailsDataModel;
    private OrderDetailsDataModel intentModel;


    @Override
    protected int layoutId() {
        return R.layout.alg_frg_personal_my_order;
    }

    @Override
    protected void initBase() {
        super.initBase();

        Bundle bundle = getArguments();
        position = bundle.getInt("position");
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){

        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        switch (position) {

            case 0:


                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylerView.setLayoutManager(linearLayoutManager);
                allFragmentAdapter = new AllFragmentAdapter(commodityModels,context,position);
                recylerView.setAdapter(allFragmentAdapter);
                allFragmentAdapter.setOnHeaderClickListener(this);
                allFragmentAdapter.setListener(this);
                break;

            case 1:

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylerView.setLayoutManager(linearLayoutManager);
                prePaymentFragmentAdapter = new PrePaymentFragmentAdapter(prePaymentCommodityModels,context,position);
                recylerView.setAdapter(prePaymentFragmentAdapter);
                prePaymentFragmentAdapter.setOnHeaderClickListener(this);

                break;

            case 2:

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylerView.setLayoutManager(linearLayoutManager);
                preSendFragmentAdapter = new PreSendFragmentAdapter(preSendCommodityModels,context,position);
                recylerView.setAdapter(preSendFragmentAdapter);
                preSendFragmentAdapter.setOnHeaderClickListener(this);
                break;

            case 3:

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylerView.setLayoutManager(linearLayoutManager);
                preReceiveFragmentAdapter = new PreReceiveFragmentAdapter(preReceiveCommodityModels,context,position);
                recylerView.setAdapter(preReceiveFragmentAdapter);
                preReceiveFragmentAdapter.setOnHeaderClickListener(this);
                break;

            case 4:

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylerView.setLayoutManager(linearLayoutManager);
                preCommentFragmentAdapter = new PreCommentFragmentAdapter(preCommentCommodityModels,context,position);
                recylerView.setAdapter(preCommentFragmentAdapter);
                preCommentFragmentAdapter.setOnHeaderClickListener(this);
                break;


        }


    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

//        List<CommodityModel> goods = CartList.getGoods();
//        List<OrderDetailsDataModel> orderDataList = CartList.getOrderDataList();

        switch (position) {
            case 0:
                presenter.loadAll();
                break;
            case 1:
                presenter.loadPrePayment();
                break;
            case 2:
                presenter.loadPreSend();
                break;
            case 3:
                presenter.loadPreReceive();
                break;
            case 4:
                presenter.loadPreCommentt();
                break;
        }


    }

    public static MyOrderFragment newInstance(int position) {
        myOrderFragment = new MyOrderFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        myOrderFragment.setArguments(bundle);
        return myOrderFragment;

    }


    @Override
    public void notifyAllOrder(PageModel<OrderDetailsDataModel> data) {
        if (data!=null&&data.dataList.size()>0){
            commodityModels.addAll(data.dataList);
            allFragmentAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void notifyPrePayment(PageModel<OrderDetailsDataModel> data) {


        prePaymentCommodityModels.addAll(data.dataList);
        prePaymentFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyPreSend(PageModel<OrderDetailsDataModel> data) {


        preSendCommodityModels.addAll(data.dataList);
        preSendFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyPreReceive(PageModel<OrderDetailsDataModel> data) {


        preReceiveCommodityModels.addAll(data.dataList);
        preReceiveFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyPreComment(PageModel<OrderDetailsDataModel> data) {


        preCommentCommodityModels.addAll(data.dataList);
        preCommentFragmentAdapter.notifyDataSetChanged();
    }
    @Override
    public void cancelOrderNotify(int cancelPosition) {
        switch (position){
            case 0: //全部订单
                allFragmentAdapter.notifyItemRemoved(cancelPosition);
                break;
            case 1:  //待付款
                prePaymentFragmentAdapter.notifyItemRemoved(cancelPosition);
                break;
            case 2:  //待发货
                preSendFragmentAdapter.notifyItemRemoved(cancelPosition);
                break;
            case 3:  //待收货
                preReceiveFragmentAdapter.notifyItemRemoved(cancelPosition);
                break;
            case 4:  //待评价
                preCommentFragmentAdapter.notifyItemRemoved(cancelPosition);
                break;
        }
    }


    @Override
    public void setOnClickListener(View view, int mposition) {
        getClickOrder(mposition);
        Intent intent;
        switch (view.getId()){
            case R.id.tv_item_my_order_view_logistics:
                startActivity(new Intent(context, SeeLogisticsActivity.class));
                break;
            case R.id.tv_item_my_order_funds:
                switch (position){
                    case 0: //全部
                        intent = new Intent(getBaseContext(), ApplyReturnMoneyActivity.class);
                        intent.putExtra("data",orderDetailsDataModel);
                        startActivity(intent);
                        break;
                    case 1: //待付款
                         intent = new Intent(context, PayListActivity.class);
                        intent.putExtra("orderDetailsDataModel",orderDetailsDataModel);
                        context.startActivity(intent);
                        break;
                    case 2: // 待发货

                        break;
                    case 3: //待收货

                        break;
                    case 4://待评价

                        startActivity(new Intent(context, CommentDetailActivity.class));

                        break;
                }
                  break;
            case R.id.tv_item_my_order_cancle:
                // TODO: 2017/9/8   我为了侧接口直接用了  实际操作需要弹出dialog
                presenter.cancelOrder(orderDetailsDataModel.orderId,mposition);
                break;

                }



        }


    @Override
    public void setOnItemClickListener(View view, int position) {
        switch (position){
            case 0: //全部订单

                intentModel = commodityModels.get(position);
                break;

            case 1:  //待付款

                intentModel = prePaymentCommodityModels.get(position);


                break;

            case 2:  //待发货

                intentModel = preSendCommodityModels.get(position);

                break;
            case 3:  //待收货
                intentModel = preReceiveCommodityModels.get(position);
                break;

            case 4:  //待评价
                intentModel = preCommentCommodityModels.get(position);

                break;


        }
        Intent intent = new Intent(context, CommonOrderDetailsActivity.class);

        intent.putExtra("orderDetailsDataModel",intentModel);
        context.startActivity(intent);
    }

    /**
     * 当前点击的条目的order对象
     * @param mposition
     */
    public void  getClickOrder(int mposition) {
        switch (position){
            case 0: //全部
                orderDetailsDataModel = commodityModels.get(mposition);
                break;
            case 1: //待付款
                orderDetailsDataModel = prePaymentCommodityModels.get(mposition);
                break;
            case 2: // 待发货
                orderDetailsDataModel = preSendCommodityModels.get(mposition);
                break;
            case 3: //待收货
                orderDetailsDataModel = preReceiveCommodityModels.get(mposition);
                break;
            case 4://待评价
                orderDetailsDataModel = preCommentCommodityModels.get(mposition);
                break;
    }
}
}
