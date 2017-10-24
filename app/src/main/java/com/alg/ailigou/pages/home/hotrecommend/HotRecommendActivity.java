package com.alg.ailigou.pages.home.hotrecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.home.hotrecommend.adapter.HotRecommendAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;

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
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于  爆款推荐
 */

public class HotRecommendActivity extends BaseMvpActivity implements HotRecommendContracts.View {
    @Inject
    HotRecommendPresenter mPresenter;
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

    private HotRecommendAdapter mAdapter;
    private List<CommodityModel> goods;
    private HotRecommendDataModel mModel;
    private View mInflateView;
    private boolean hasNext;


    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
        goods = new ArrayList<>();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mAdapter.setLiftClickListener(new HotRecommendAdapter.ItemLiftClickListener() {
            @Override
            public void setOnItemLiftClickListener(View view, int potison) {
                Intent intent = new Intent(HotRecommendActivity.this, CommodityDetailsActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.setRightClickListener(new HotRecommendAdapter.ItemRightClickListener() {
            @Override
            public void setOnItemRightClickListener(View view, int potison) {
                Intent intent = new Intent(HotRecommendActivity.this, CommodityDetailsActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_recommend_head_1:
                        Intent intent = new Intent(HotRecommendActivity.this, CommodityDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.ll_recommend_head_2:
                        intent = new Intent(HotRecommendActivity.this, CommodityDetailsActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });


        //下拉刷新
        storeHousePtrFrame.disableWhenHorizontalMove(true);
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.page=1;
                mPresenter.loadData(mPresenter.page, mPresenter.pageSize);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        mTvBaseTitle.setText("");
        mTvBaseTitle.setWidth(MeasureUtils.dp2px(66));
        mTvBaseTitle.setHeight(MeasureUtils.dp2px(16));
        mTvBaseTitle.setBackgroundResource(R.drawable.alg_home_recommend_title);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_home_recommend_left_arrow);

        mAdapter = new HotRecommendAdapter(goods, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(manager);
        mRecylerView.setAdapter(mAdapter);
        mInflateView = LayoutInflater.from(this).inflate(R.layout.alg_item_hot_recommend_header, mRecylerView, false);
        mAdapter.addHeaderView(mInflateView);
        mAdapter.setLoadMoreListenter(mRecylerView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (hasNext){
                    mPresenter.loadData(mPresenter.page,mPresenter.pageSize);
                }
            }
        });
        mPresenter.loadData(mPresenter.page, mPresenter.pageSize);

    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_recylerview_width_title;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void notify(HotRecommendDataModel model) {

        if (model != null) {
            mModel = model;
            mAdapter.setDataModel(model);
            if (model.recommendGoods.page == 1) {//要么是第一次  要么是刷新  清空集合
                goods.clear();
            }
            mAdapter.setDataModel(model);//为了让item 拿到出场入场时间 还有banner
            hasNext = model.recommendGoods.hasNext;
            mPresenter.page = model.recommendGoods.page+1;
            goods.addAll(model.recommendGoods.dataList);
            mAdapter.setLoading(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshComplete() {
        if (storeHousePtrFrame.isRefreshing()) {
            storeHousePtrFrame.refreshComplete();
        }
    }


}
