package com.alg.ailigou.pages.home.hotsalelist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.hotsalelist.adapter.HotSaleRecycleAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 热销榜
 */

public class HotSaleListActivity extends BaseMvpActivity implements HotSaleListContracts.View, OnClickListener {

    @Inject
    HotSaleListPresenter hotSaleListPresenter;

    @BindView(R.id.iv_home_hot_sale_list_banner)
    ImageView ivHomeHotSaleListBanner;
    @BindView(R.id.recyler_view)
    CustomRecyclerView recylerView;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    @BindView(R.id.customScollView)
    CustomScrollView customScollView;

    @BindView(R.id.refresh_view)
    RefreshFrameLayout refreshView;


    private boolean hasNext;

    private List<CommodityModel> commodityModels = new ArrayList<>();


    private LinearLayoutManager linearLayoutManager;
    private HotSaleRecycleAdapter hotSaleRecycleAdapter;


    @Override
    protected BasePresenter getPresenter() {
        return hotSaleListPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_hot_sale_list;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setText("");
        tvBaseTitle.setWidth(MeasureUtils.dp2px(50));
        tvBaseTitle.setHeight(MeasureUtils.dp2px(16));
        tvBaseTitle.setBackgroundResource(R.drawable.alg_home_icon_hot_sale);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_icon_back_arrow);

        customScollView.setOnScrollToBottomLintener(new CustomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom){
                    if (hasNext) {
                        hotSaleListPresenter.loadHotSaleGoodsData(hotSaleListPresenter.page, hotSaleListPresenter.pageSize);
                    }
                }
            }
        });
        //在这里请求数据
        hotSaleListPresenter.loadHotSaleGoodsData(hotSaleListPresenter.page, hotSaleListPresenter.pageSize);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
//        recylerView.setNestedScrollingEnabled(false);
        recylerView.setLayoutManager(linearLayoutManager);
        hotSaleRecycleAdapter = new HotSaleRecycleAdapter(commodityModels, this);
        recylerView.setAdapter(hotSaleRecycleAdapter);




        hotSaleRecycleAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });

        hotSaleRecycleAdapter.setOnHeaderClickListener(this);

        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                commodityModels.clear();
                hotSaleListPresenter.page= 1;
                hotSaleListPresenter.loadHotSaleGoodsData( hotSaleListPresenter.page,hotSaleListPresenter.pageSize);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    @Override
    public void updateHotSaleData(HomeHotSaleCommodityModel models) {

        if (models != null) {
            if (models.goodsPage.page == 1) {

                //第一次加载顶部banner
                ImageUtils.load(HotSaleListActivity.this, models.bannerModel.image, ivHomeHotSaleListBanner);
            }

            hasNext = models.goodsPage.hasNext;
            hotSaleListPresenter.page = models.goodsPage.page + 1;
            commodityModels.addAll(models.goodsPage.dataList);

            hotSaleRecycleAdapter.notifyDataSetChanged();

            hotSaleRecycleAdapter.setLoading(false);
        }
    }

    @Override
    public void updateTablayoutTitle(List<String> tabTitles) {

    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void refreshComplete() {
        super.refreshComplete();
        RefreshCompleteUtils.refreshComplete(refreshView);
    }

    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {


        }
    }



}
