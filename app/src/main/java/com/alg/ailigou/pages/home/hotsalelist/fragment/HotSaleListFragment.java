package com.alg.ailigou.pages.home.hotsalelist.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.home.hotsalelist.adapter.HotSaleListFragmentRecycleAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于 热销榜的fragment
 */

public class HotSaleListFragment extends BaseMvpFragment  implements HotSaleListFragmentContracts.View, OnClickListener {

    @Inject
    HotSaleListFragmentPresenter hotSaleListFragmentPresenter;


    private static HotSaleListFragment hotSaleListFragment;
    @BindView(R.id.recyler_view_hot_sale_list)
    RecyclerView recylerViewHotSaleList;

    private List<CommodityModel> goods;
    private HotSaleListFragmentRecycleAdapter hotSaleListFragmentRecycleAdapter;
    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected BasePresenter getPresenter() {
        return hotSaleListFragmentPresenter;
    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        Bundle arguments = getArguments();
        int position = arguments.getInt("position");
        //这里拿到填充fragment的接口的数据,更急posiotion的值请求在Presenter请求不同的接口
        hotSaleListFragmentPresenter.loadData(hotSaleListFragmentPresenter.page,hotSaleListFragmentPresenter.pageSize);
        //// TODO: 2017/7/21  因为接口没有通我们现在这里模拟假数据
        initData();
        //更新ui
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerViewHotSaleList.setLayoutManager(linearLayoutManager);
        hotSaleListFragmentRecycleAdapter = new HotSaleListFragmentRecycleAdapter(goods,this.getContext());
        recylerViewHotSaleList.setAdapter(hotSaleListFragmentRecycleAdapter);
        hotSaleListFragmentRecycleAdapter.setOnClickListener(this);
    }

    private void initData() {
        goods = CartList.getGoods();

    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_home_hot_sale_list;
    }


    public static HotSaleListFragment newInstance(int position) {
        hotSaleListFragment = new HotSaleListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        hotSaleListFragment.setArguments(bundle);
        return hotSaleListFragment;
    }


    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()){

            case R.id.iv_item_hot_sale_icon:

                break;


            case R.id.tv_item_hot_sale_save:

                break;

            case R.id.linearLayout_item_hot_sale_list:
                break;
        }



    }




    @Override
    public void notify(HomeHotSaleCommodityModel commodityModels) {

    }
}
