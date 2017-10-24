package com.alg.ailigou.pages.home.everydaycheap;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.everydaycheap.adapter.EveryDayCheapAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于  天天特价
 */

public class EveryDayCheapActivity extends BaseMvpActivity implements EveryDayCheapContracts.View {
    @Inject
    EveryDayCheapPresenter mPresenter;
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
    private EveryDayCheapAdapter mAdapter;
    private List<CommodityModel> mModels;

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
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_every_day_cheap;
    }

    @Override
    protected void initBase() {
        super.initBase();
        mModels = new ArrayList<>();
    }

    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EveryDayCheapAdapter(mModels, this);
        mRecylerView.setAdapter(mAdapter);
    }

    @Override
    public void notifity(List<CommodityModel> modelList) {
        mModels.addAll(modelList);
        mAdapter.notifyDataSetChanged();
    }
}
