package com.alg.ailigou.pages.home.luxurybrand;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.common.model.LuxuryBrandHeaderModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.luxurybrand.adapter.LuxuryBrandAdapter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class LuxuryBrandActivity extends BaseMvpActivity implements LuxuryBrandContracts.View {
    @Inject
    LuxuryBrandPresenter mPresenter;
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
    private List<CommodityModel> mCommodityModels;
    private LuxuryBrandHeaderModel models;
    private LuxuryBrandAdapter mAdapter;


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
        mCommodityModels = new ArrayList<>();

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("奢侈品牌");
        initRecylerView();

    }

    private void initRecylerView() {
        mAdapter = new LuxuryBrandAdapter(mCommodityModels, this, models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(linearLayoutManager);
        mRecylerView.setAdapter(mAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.alg_item_home_luxury_brand_header, mRecylerView, false);
        mAdapter.addHeaderView(view);
        //// TODO: 2017/7/20   假数据
        mCommodityModels.addAll(CartList.getGoods());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });
        mAdapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods1:
                        break;
                    case R.id.ll_goods2:
                        break;
                    case R.id.ll_goods3:
                        break;
                    case R.id.ll_goods4:
                        break;
                    case R.id.ll_brand_goods1:
                        break;
                    case R.id.ll_brand_goods2:
                        break;
                    case R.id.ll_brand_goods3:
                        break;
                    case R.id.ll_brand_goods4:
                        break;
                    case R.id.ll_brand_goods5:
                        break;
                    case R.id.ll_brand_goods6:
                        break;
                    case R.id.ll_brand_goods7:
                        break;
                    case R.id.ll_brand_goods8:
                        break;
                }
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_alg_choice;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setTopData(List<HomeCommodityTypeModel> list) {

    }

    @Override
    public void notifity(List<CommodityModel> modelList) {

    }
}
