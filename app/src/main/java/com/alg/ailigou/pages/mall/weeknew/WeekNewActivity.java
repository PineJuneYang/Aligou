package com.alg.ailigou.pages.mall.weeknew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.WeekNewGoodsDataModel;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.alg.ailigou.pages.mall.weeknew.adapter.WeekNewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.weeknew
 * Created by Chris Chen on 2017/7/21 16:13.
 * Explain:每周上新
 */

public class WeekNewActivity extends BaseMvpActivity implements WeekNewContracts.View ,IntentKeys{
    @Inject
    WeekNewPresenter presenter;


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
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.store_house_ptr_frame)
    RefreshFrameLayout storeHousePtrFrame;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    private List<CommodityModel> goods;
    private WeekNewAdapter adapter;


    private List<CommodityModel> commodityModels = new ArrayList<>();
    private View view;
    private ImageView ivWeekNew;
    private TextView tvWeekNew;
    public boolean hasNext;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_recylerview_width_title;
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        mTvBaseTitle.setText("");
        mTvBaseTitle.setWidth(MeasureUtils.dp2px(66));
        mTvBaseTitle.setHeight(MeasureUtils.dp2px(16));
        mTvBaseTitle.setBackgroundResource(R.drawable.alg_home_icon_week_new);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_icon_back_arrow);

//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) vpMainPages.getLayoutParams();
//        params.setMargins(0, ScreenUtils.getStatueBarHeight(), 0, 0);
//        vpMainPages.setLayoutParams(params);

        presenter.loadData();
        initRecylerView();
    }

    private void initRecylerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecylerView.setLayoutManager(gridLayoutManager);
        adapter = new WeekNewAdapter(commodityModels, this);
        mRecylerView.setAdapter(adapter);

        View view = LayoutInflater.from(this).inflate(R.layout.alg_inc_common_image, mRecylerView, false);
        ivWeekNew = (ImageView) view.findViewById(R.id.iv_banner);
        adapter.addHeaderView(view);
        adapter.setLoadMoreListenter(mRecylerView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (presenter.hasNext){

                    presenter.loadData();
                }
            }
        });

        adapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(WeekNewActivity.this,CommodityDetailsActivity.class);
                intent.putExtra(GOODS_ID,commodityModels.get(position-1));
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        //注释  这个 不是 头部的点击事件 ,是item里面的立即购买的点击事件 我没有重新命名请注意
        adapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {

            }
        });
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(WeekNewActivity.this, CommodityDetailsActivity.class);
                startActivity(intent);
            }
        });

        storeHousePtrFrame.disableWhenHorizontalMove(true);
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.page = 1;
                commodityModels.clear();

                presenter.loadData();
            }


            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void updateWeekNewActivity(WeekNewGoodsDataModel weekNewGoodsDataModel) {

//        if (weekNewGoodsDataModel.mPageModel.page == 1) {
//            //第一次请求
//            Glide.with(this).load("http://192.168.1.107/upload/images/alg_home_header_action_banner_03.jpg").into(ivWeekNew);
//        }
        presenter.hasNext = weekNewGoodsDataModel.mPageModel.hasNext;
        presenter.page = weekNewGoodsDataModel.mPageModel.page + 1;

        commodityModels.addAll(weekNewGoodsDataModel.mPageModel.dataList);
        adapter.setBannerModel(weekNewGoodsDataModel.mBannerModel);
        adapter.notifyDataSetChanged();
        adapter.setLoading(false);

    }


    @Override
    public void refreshComplete() {
        super.refreshComplete();
        RefreshCompleteUtils.refreshComplete(storeHousePtrFrame);
    }
}
