package com.alg.ailigou.pages.news.fragments.newrelease;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.news.fragments.adapter.NewsAdapter;
import com.alg.ailigou.pages.news.inject.DaggerNewsComponent;
import com.alg.ailigou.pages.news.inject.NewsMoudule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/12.
 * 此类或接口用于  新品发布
 */

public class NewRealeseFragment extends BaseMvpFragment implements NewRealeseContrats.View {
    @Inject
    NewRealesePresenter mPresenter;

    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    private NewsAdapter mNewsAdapter;

    private List<NewsModel> news = new ArrayList<>();

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected NewRealesePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_news_alg_moving;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mNewsAdapter = new NewsAdapter(news, getBaseContext());
        mRecylerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecylerView.setAdapter(mNewsAdapter);
        mPresenter.loadNewsList(0, 0, 20);
    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerNewsComponent.builder().newsMoudule(new NewsMoudule(this)).build().inject(this);

    }

    @Override
    public void adapterNotifity(List<NewsModel> newsModels) {
        news.addAll(newsModels);
        mNewsAdapter.notifyDataSetChanged();
    }
}
