package com.alg.ailigou.pages.union.uniondetail.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.union.uniondetail.adapter.UnionShowAdapter;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于 商家展示
 */

public class UnionShowFragment extends BaseMvpFragment {

    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    private UnionShowAdapter mAdapter;
    private List<String> urls;

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initBase() {
        super.initBase();
        urls = new ArrayList<>();
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_reaylerview;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mRecylerView.setLayoutManager(new LinearLayoutManager(context));

        mAdapter = new UnionShowAdapter(urls, getBaseContext());
        mRecylerView.setAdapter(mAdapter);
    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    @Subscribe(
            tags = {
                    @Tag("UnionDetailActivity")
            }
    )
    public void getData(UnionModel mUnionModel) {
        urls.addAll(mUnionModel.unionUrls);
        mAdapter.notifyDataSetChanged();
    }
}
