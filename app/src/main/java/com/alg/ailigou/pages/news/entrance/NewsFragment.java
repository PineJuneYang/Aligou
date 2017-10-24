package com.alg.ailigou.pages.news.entrance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
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

/**
 * AiligouApp
 * com.alg.ailigou.pages.news
 * Created by Chris Chen on 2017/7/7 15:12.
 * Explain:新闻资讯
 */

public class NewsFragment extends BaseMvpFragment implements NewsContrats.View {
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
