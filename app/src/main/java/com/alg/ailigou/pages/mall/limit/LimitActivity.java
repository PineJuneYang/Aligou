package com.alg.ailigou.pages.mall.limit;

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
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.alg.ailigou.pages.mall.limit.adapter.LimitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.RefreshFrameLayout;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.limit
 * Created by Chris Chen on 2017/7/21 15:57.
 * Explain:限时特购  现在变成了 精选特价
 */

public class LimitActivity extends BaseMvpActivity implements LimitContracts.View , IntentKeys{
    @Inject
    LimitPresenter presenter;
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
    RefreshFrameLayout mRefreshFrameLayout;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    private LimitAdapter adapter;
    private List<CommodityModel> mCommodityModels;
    private int pageSize = 10;
    private int page =1;
    private boolean hasNext = false;
    private Subscription mTimerSubscribe;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_recylerview_width_title;
    }

    @Override
    protected void initBase() {
        super.initBase();
        mCommodityModels = new ArrayList<>();
        setCountTimer();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        mTvBaseTitle.setText("");
        mTvBaseTitle.setWidth(MeasureUtils.dp2px(66));
        mTvBaseTitle.setHeight(MeasureUtils.dp2px(16));
        mTvBaseTitle.setBackgroundResource(R.drawable.alg_home_featured_specials);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_home_back_arrow);

        mRecylerView.setBackgroundResource(R.color.alg_common_pinke);

        initRecylerView();
        presenter.LoadData(1, 10);
    }

    private void initRecylerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(linearLayoutManager);
        adapter = new LimitAdapter(mCommodityModels, this);
        mRecylerView.setAdapter(adapter);
        View view = LayoutInflater.from(this).inflate(R.layout.alg_item_mall_limit_header, mRecylerView, false);
        adapter.addHeaderView(view);

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
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
                Intent intent = new Intent(LimitActivity.this, CommodityDetailsActivity.class);
                intent.putExtra(GOODS_ID,mCommodityModels.get(position-1).id);
                startActivity(intent);
            }
        });
        adapter.setLoadMoreListenter(mRecylerView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (hasNext){
                    presenter.LoadData(page,pageSize);
                }
            }
        });

        //下拉刷新
        mRefreshFrameLayout.disableWhenHorizontalMove(true);
        mRefreshFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page  = 0;
                presenter.LoadData(page, pageSize);
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
    public void notify(ChoiceCheapGoodsDataModel model) {
        if (model.goods != null) {
            hasNext = model.goods.hasNext;
            if (model.goods.page == 1) {
                mCommodityModels.clear();
            }
            page = model.goods.page + 1;
            hasNext = model.goods.hasNext;
            adapter.setDataModel(model);
            mCommodityModels.addAll(model.goods.dataList);
            adapter.notifyDataSetChanged();
            adapter.setLoading(false);
        }
    }

    @Override
    public void refreshComplete() {
        if (mRefreshFrameLayout.isRefreshing()) {
            mRefreshFrameLayout.refreshComplete();
        }
    }

    /**
     * 倒计时
     */
    private void setCountTimer() {
        mTimerSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).map(new Func1<Long, Long>() {
            @Override
            public Long call(Long aLong) {
                //该方法在子线程执行
                if (mCommodityModels != null && mCommodityModels.size() > 0) {
                    for (int i = 0; i < mCommodityModels.size(); i++) {
                        mCommodityModels.get(i).countDown -= 1000;
                    }
                }
                return aLong;
            }
        }).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                //该方法在主线程执行
                if ((mCommodityModels != null && mCommodityModels.size() > 0) && adapter != null) {
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimerSubscribe != null && !mTimerSubscribe.isUnsubscribed()) {
            mTimerSubscribe.unsubscribe();
        }
    }


}
