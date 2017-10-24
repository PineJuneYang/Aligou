package com.alg.ailigou.pages.mall.goodslist.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.wine.adapter.WineHorziontalAdapter;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.andview.refreshview.XRefreshView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于
 */

public class GoodsListFragment extends BaseMvpFragment implements GoodsListFragmentContracts.View {
    @BindView(R.id.xrefreshview_goods_list)
    XRefreshView xrefreshviewGoodsList;
    Unbinder unbinder;
    private List<CommodityModel> horizontalDatas = CartList.getGoods();//水平的recylerview数据
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;

    @Inject
    GoodsListFragmentPresenter goodsListFragmentPresenter;
    private static GoodsListFragment goodsListFragment;
    private WineHorziontalAdapter wineHorziontalAdapter;
    private int position;

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected BasePresenter getPresenter() {
        return goodsListFragmentPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_mall_goods_list;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
//        DaggerHomeComponent.builder().AcitivtyModule(new HomeModule(this)).build().inject(this);
        DaggerMallComponent.builder().activityModule(new ActivityModule(this.getBaseActivity())).build().inject(this);
    }

    @Override
    protected void initBase() {
        super.initBase();
        Bundle arguments = getArguments();
        position = arguments.getInt("position");
        //设置自动下拉加载
        xrefreshviewGoodsList.setPullLoadEnable(true);

        mRecylerView.setHasFixedSize(true);

    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        goodsListFragmentPresenter.loadData(position);

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();


        mRecylerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));


        wineHorziontalAdapter = new WineHorziontalAdapter(horizontalDatas, getBaseContext());
        mRecylerView.setAdapter(wineHorziontalAdapter);
    }


    public static GoodsListFragment newInstance(int position) {
        goodsListFragment = new GoodsListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        goodsListFragment.setArguments(bundle);
        return goodsListFragment;
    }


    @Override
    public void notify(List<CommodityModel> commodityModels) {
        horizontalDatas.addAll(commodityModels);
        wineHorziontalAdapter.notifyDataSetChanged();
    }



}
