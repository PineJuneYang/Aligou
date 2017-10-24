package com.alg.ailigou.pages.union.entrance;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.union.entrance.adapter.UniconAdapter;
import com.alg.ailigou.pages.union.inject.DaggerUnionComponent;
import com.alg.ailigou.pages.union.inject.UnionModule;
import com.alg.ailigou.pages.union.uniondetail.UnionDetailActivity;
import com.alg.ailigou.pages.union.unionsearch.UnionSearchActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.union
 * Created by Chris Chen on 2017/7/25 17:19.
 * Explain:联盟商家 主模块页面
 */

public class UnionFragment extends BaseMvpFragment implements UnionContracts.View {
    @Inject
    UnionPresenter presenter;


    @BindView(R.id.iv_base_top_bar_search)
    ImageView mIvHomeTopBarSearch;
    @BindView(R.id.ll_base_search_bar_container)
    RelativeLayout mLlSearchBarContainer;

    @BindView(R.id.ll_base_top_bar_container)
    LinearLayout mLlHomeTopBarContainer;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.tv_base_search)
    TextView tvBaseSearch;
    @BindView(R.id.tv_base_setting)
    TextView tvBaseSetting;

    private List<UnionModel> mUnionModels = new ArrayList<>();
    private boolean hasNest = true;

    private UniconAdapter mAdapter;

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

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
    protected void afterContentView() {
        super.afterContentView();
        presenter.loadData("");
        initRecylerView();
        ivBaseBack.setVisibility(View.GONE);
        tvBaseSetting.setVisibility(View.GONE);

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

    @OnClick(R.id.ll_base_search_bar_container)
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
