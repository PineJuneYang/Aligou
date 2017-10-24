package com.alg.ailigou.pages.news.entrance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.news.fragments.adapter.NewsAdapter;
import com.alg.ailigou.pages.news.inject.DaggerNewsComponent;
import com.alg.ailigou.pages.news.inject.NewsMoudule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于
 */

public class NewsActivity extends BaseMvpActivity implements NewsContrats.View {
    @Inject
    NewsPresenter mPresenter;
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
    RefreshFrameLayout mStoreHousePtrFrame;

    private NewsAdapter mNewsAdapter;

    private List<NewsModel> newsModels;

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
        newsModels = new ArrayList<>();
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_recylerview_width_title;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerNewsComponent.builder().newsMoudule(new NewsMoudule(this)).build().inject(this);

    }

    @OnClick(R.id.ll_base_notice)
    public void onViewClicked() {

    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mLlBaseNotice.setVisibility(View.VISIBLE);
        mLlBaseBack.setVisibility(View.GONE);
        mTvBaseTitle.setText("爱利购资讯");
        mStoreHousePtrFrame.setResistance(1.7f);
        mStoreHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mStoreHousePtrFrame.setDurationToClose(200);
        mStoreHousePtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mStoreHousePtrFrame.setPullToRefresh(false);
        // default is true
        mStoreHousePtrFrame.setKeepHeaderWhenRefresh(true);
        mStoreHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStoreHousePtrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mNewsAdapter = new NewsAdapter(newsModels, getBaseContext());
        mRecylerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecylerView.setAdapter(mNewsAdapter);
        mPresenter.loadNewsList(0, 0, 20);

    }


    @Override
    public void adapterNotifity(List<NewsModel> newsModelList) {
        if (newsModelList != null && newsModelList.size() > 0) {
            this.newsModels.addAll(newsModelList);
            mNewsAdapter.notifyDataSetChanged();
        }
    }


}
