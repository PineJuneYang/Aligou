package com.alg.ailigou.pages.home.goodslist.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.goodslist.adapter.GoodsListFragmentRecycleAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.wine.adapter.WineHorziontalAdapter;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 玖泞
 * on 2017/7/19.
 * 此类或接口用于
 */

public class GoodsListFragment extends BaseMvpFragment implements GoodsListFragmentContracts.View {
    @BindView(R.id.xrefreshview_goods_list)
    XRefreshView xrefreshviewGoodsList;

    private List<CommodityModel> commodityModels = new ArrayList<>();
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;

    @Inject
    GoodsListFragmentPresenter presenter;
    private static GoodsListFragment goodsListFragment;
    private WineHorziontalAdapter wineHorziontalAdapter;
    private int position;
    private GoodsListFragmentRecycleAdapter goodsListFragmentRecycleAdapter;

    public boolean isHasNext;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_mall_goods_list;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
//        DaggerHomeComponent.builder().AcitivtyModule(new HomeModule(this)).build().inject(this);
//        DaggerMallComponent.builder().activityModule(new ActivityModule(this.getBaseActivity())).build().inject(this);
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
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

        presenter.loadData();

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();


        mRecylerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();

        xrefreshviewGoodsList.setPinnedTime(1000);
        xrefreshviewGoodsList.setMoveForHorizontal(true);


        goodsListFragmentRecycleAdapter = new GoodsListFragmentRecycleAdapter(context, commodityModels);


        //当需要使用数据不满一屏时不显示点击加载更多的效果时，解注释下面的三行代码
        //并注释掉第四行代码
        goodsListFragmentRecycleAdapter.setCustomLoadMoreView(new XRefreshViewFooter(context));


        mRecylerView.setAdapter(goodsListFragmentRecycleAdapter);

        xrefreshviewGoodsList.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        xRefreshView.stopRefresh();
//                    }
//                }, 2000);

                commodityModels.clear();
                presenter.loadData();


            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if (isHasNext){
                    presenter.loadData();
                }

            }
        });
    }


    public static GoodsListFragment newInstance(int position) {
        goodsListFragment = new GoodsListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        goodsListFragment.setArguments(bundle);
        return goodsListFragment;
    }


    @Override
    public void notify(PageModel<CommodityModel> commodityModelPageModel) {


        isHasNext = commodityModelPageModel.hasNext;
        presenter.page = commodityModelPageModel.page+1;
        commodityModels.addAll(commodityModelPageModel.dataList);
        goodsListFragmentRecycleAdapter.notifyDataSetChanged();
        //刷新也是请求第一页
        xrefreshviewGoodsList.stopRefresh();
        xrefreshviewGoodsList.stopLoadMore();
    }
}
