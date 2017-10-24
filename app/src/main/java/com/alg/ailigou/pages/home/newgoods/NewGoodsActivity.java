package com.alg.ailigou.pages.home.newgoods;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.newgoods.adapter.NewGoodsAdapter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.newsgoods
 * Created by Chris Chen on 2017/7/13 13:54.
 * Explain:新品
 */

public class NewGoodsActivity extends BaseMvpActivity implements NewGoodsContracts.View {
    @Inject
    NewGoodsPresenter presenter;



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
    private List<CommodityModel> mModels = new ArrayList<>();
    private NewGoodsAdapter mAdapter;
    private boolean hasNext = true;
    private int page = 0;



    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_newgoods;
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mLlBaseNotice.setVisibility(View.GONE);
        mLlBaseEdit.setVisibility(View.GONE);
        mTvBaseTitle.setText("新品");
        presenter.loadData(page, 20);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mRecylerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new NewGoodsAdapter(mModels, this);
        mRecylerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreListenter(mRecylerView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                presenter.loadData(page, 20);
            }
        });

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }


    @Override
    public void notifity(PageModel<CommodityModel> pageModel) {
        if (pageModel != null) {
            if (page==1){
                mModels.clear();
            }
            hasNext = pageModel.hasNext;
            page++;
            mModels.addAll(pageModel.dataList);
            mAdapter.notifyDataSetChanged();
        }
    }




    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }
}
