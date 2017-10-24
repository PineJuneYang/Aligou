package com.alg.ailigou.pages.union.entrance;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.requestmodel.SearchUnionRequest;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.union.entrance.adapter.UniconAdapter;
import com.alg.ailigou.pages.union.inject.DaggerUnionComponent;
import com.alg.ailigou.pages.union.inject.UnionModule;
import com.alg.ailigou.pages.union.unionsearch.UnionSearchActivity;
import com.alg.ailigou.pages.union.uniondetail.UnionDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.union
 * Created by Chris Chen on 2017/7/24 10:20.
 * Explain:联盟商家
 */

public class UnionActivity extends BaseMvpActivity implements UnionContracts.View {
    @Inject
    UnionPresenter presenter;
    @BindView(R.id.tv_home_top_bar_location_city)
    TextView mTvHomeTopBarLocationCity;
    @BindView(R.id.iv_home_location_arrow)
    ImageView mIvHomeLocationArrow;
    @BindView(R.id.iv_home_top_bar_search)
    ImageView mIvHomeTopBarSearch;
    @BindView(R.id.ll_search_bar_container)
    LinearLayout mLlSearchBarContainer;
    @BindView(R.id.iv_home_scan)
    ImageView mIvHomeNotice;
    @BindView(R.id.ll_home_top_bar_container)
    LinearLayout mLlHomeTopBarContainer;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
   private SearchUnionRequest mRequest;

    private List<UnionModel> mUnionModels = new ArrayList<>();
    private boolean hasNest = true;

    private UniconAdapter mAdapter;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_home_union;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerUnionComponent.builder().unionModule(new UnionModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        presenter.loadData("");
        initRecylerView();
        mTvHomeTopBarLocationCity.setVisibility(View.GONE);
        mIvHomeLocationArrow.setVisibility(View.GONE);
        mIvHomeNotice.setVisibility(View.GONE);
    }


    @Override
    protected void initBase() {
        super.initBase();
      mRequest= (SearchUnionRequest) getIntent().getSerializableExtra("request");
    }

    //recylerView 的一些操作
    private void initRecylerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecylerView.setLayoutManager(linearLayoutManager);
        mAdapter = new UniconAdapter(mUnionModels, getBaseContext());
        mRecylerView.setAdapter(mAdapter);
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(getBaseContext(), UnionDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.ll_search_bar_container)
    public void onViewClicked() {
        startActivity(UnionSearchActivity.class);
    }


    @Override
    public void notify(PageModel<UnionModel> pageModel) {
        if (pageModel.dataList.size() > 0) {
            hasNest = pageModel.hasNext;
            mUnionModels.addAll(pageModel.dataList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
