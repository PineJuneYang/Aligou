package com.alg.ailigou.pages.home.hotsaleweek.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.wine.adapter.WineHorziontalAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于
 */

public class HotSaleWeekFragment extends BaseMvpFragment implements HotSaleWeekFragmentContracts.View {
    private List<CommodityModel> horizontalDatas = CartList.getGoods();//水平的recylerview数据
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;

    @Inject
    HotSaleWeekFragmentPresenter hotSaleWeekFragmentPresenter;
    private static HotSaleWeekFragment hotSaleWeekFragment;
    private WineHorziontalAdapter wineHorziontalAdapter;
    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };

    @Override
    protected BasePresenter getPresenter() {
        return hotSaleWeekFragmentPresenter;
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
        hotSaleWeekFragmentPresenter.loadData(position);

        mRecylerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        wineHorziontalAdapter = new WineHorziontalAdapter(horizontalDatas, getBaseContext());
        mRecylerView.setAdapter(wineHorziontalAdapter);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_hot_sale_week;
    }

    public static HotSaleWeekFragment newInstance(int position) {
        hotSaleWeekFragment = new HotSaleWeekFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        hotSaleWeekFragment.setArguments(bundle);
        return hotSaleWeekFragment;
    }

    @Override
    public void notify(List<CommodityModel> commodityModels) {
        horizontalDatas.addAll(commodityModels);
        wineHorziontalAdapter.notifyDataSetChanged();
    }
}
