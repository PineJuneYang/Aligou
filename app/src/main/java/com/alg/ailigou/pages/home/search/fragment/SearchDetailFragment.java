package com.alg.ailigou.pages.home.search.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.adapter.SearchDetailFragmentAdapter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 玖泞
 * on 2017/7/26
 * 此类或接口用于 搜索热词后展示的fragment
 */

public class SearchDetailFragment extends BaseMvpFragment implements SearchDetailFragmentContracts.View, OnClickListener,IntentKeys {


    @Inject
    SearchDetailFragmentPresenter presenter;

    @BindView(R.id.recycleView_home_search)
    RecyclerView mRecycleViewHomeSearch;
    @BindView(R.id.refresh_view)
    RefreshFrameLayout refreshView;


    private int position;
    public boolean hasNext = false;

    //请求第几页数据
    private int requestPage = 0;
    private int pageSize;

    private List<CommodityModel> commodityModels;
    private SearchDetailFragmentAdapter searchDetailFragmentAdapter;
    private View footerView;
    private String searchWords;

//    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
//        @Override
//        public void onLoadMore() {
//            if (hasNext){
//                presenter.page++;
//                presenter.loadData("洋河", 0);
//            }
//        }
//    };


    public static SearchDetailFragment newInstance(String searchWords, int position) {
        SearchDetailFragment searchDetailFragment = new SearchDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString("searchWords", searchWords);
        bundle.putInt("position", position);


        searchDetailFragment.setArguments(bundle);
        return searchDetailFragment;
    }

    @Override
    protected void initBase() {
        Bundle arguments = getArguments();
        searchWords = arguments.getString("searchWords");
        position = arguments.getInt("position");
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_home_search;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        presenter.loadData(searchWords, (position + 1) * 2);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        commodityModels = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleViewHomeSearch.setLayoutManager(gridLayoutManager);
        searchDetailFragmentAdapter = new SearchDetailFragmentAdapter(context, commodityModels);
        mRecycleViewHomeSearch.setAdapter(searchDetailFragmentAdapter);
        searchDetailFragmentAdapter.setLoadMoreListenter(mRecycleViewHomeSearch, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {

                if (hasNext) {
                    presenter.loadData(searchWords, (position + 1) * 2);
                }


            }
        });


        footerView = LayoutInflater.from(context).inflate(R.layout.alg_act_item_search_detail_footer, mRecycleViewHomeSearch, false);

//        searchDetailFragmentAdapter.setmMoreDataListener(this);
        searchDetailFragmentAdapter.addFootView(footerView);
        searchDetailFragmentAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(context, CommodityDetailsActivity.class);
                intent.putExtra(GOODS_ID,commodityModels.get(position).id);
                startActivity(intent);
            }
        });



        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                commodityModels.clear();
                presenter.page = 0;
                presenter.loadData(searchWords,(position + 1) * 2);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @Override
    public void notify(PageModel<CommodityModel> commodityModelPageModel) {

        if (commodityModelPageModel.page == 1) {
            //说明是开始加载的数据
            pageSize = commodityModelPageModel.count;
            requestPage = commodityModelPageModel.page + 1;

        } else {
            footerView.setVisibility(View.GONE);
        }


        presenter.page = commodityModelPageModel.page + 1;
        hasNext = commodityModelPageModel.hasNext;

        commodityModels.addAll(commodityModelPageModel.dataList);
        searchDetailFragmentAdapter.notifyDataSetChanged();
        searchDetailFragmentAdapter.setLoading(false);

    }


    @Override
    public void setOnClickListener(View view, int position) {

    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    @Subscribe(
            tags = {
                    @Tag("SearchDetailActivity")
            }
    )
    public void getData(Object status) {
        searchWords = (String) status;
//        if (position == COMMODITY_FRAGMENT) {
//            if (s.equals("编辑")) {
//                goodsAdapter.setEdit(true);
//                rlMyCollectionFragmentAllChooseDelete.setVisibility(View.VISIBLE);
//
//            } else {
//                goodsAdapter.setEdit(false);
//                rlMyCollectionFragmentAllChooseDelete.setVisibility(View.GONE);
//            }
//        }

        ToastUtils.showToast(context, "searchWords" + searchWords);

    }

    @Override
    public void refreshComplete() {
        super.refreshComplete();
        RefreshCompleteUtils.refreshComplete(refreshView);
    }
}
