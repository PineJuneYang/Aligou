package com.alg.ailigou.pages.classification.fragments.hot;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.classification.fragments.adapter.HotAdapter;
import com.alg.ailigou.pages.classification.fragments.adapter.HotRecycleAdapter;
import com.alg.ailigou.pages.classification.inject.DaggerClassificationComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.hot
 * Created by Chris Chen on 2017/7/11 13:08.
 * Explain:
 */

public class HotFragment extends BaseMvpFragment implements HotContracts.View {
    BannerViewPager bvpCommodity;//商品轮播广告
    RecyclerView rvNewCommodityArea;//新品专场
    @BindView(R.id.rv_good_commodity_list)
    RecyclerView rvGoodCommodityList;//好货榜单

    Unbinder unbinder;


    private List<CommodityTypeModel> goodsTypeList = new ArrayList<>();

    @Inject
    HotPresenter presenter;
    private HotRecycleAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    BannerAdapter<BannerModel> bannerAdapter;
    HotAdapter newCommodityAdapter;


    @Override
    protected int layoutId() {
        return R.layout.alg_frg_classification_type_hot;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerClassificationComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        rvGoodCommodityList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HotRecycleAdapter(goodsTypeList, getContext());
        rvGoodCommodityList.setAdapter(adapter);


        View headerView = LayoutInflater.from(context).inflate(R.layout.alg_head_classification_hot, rvGoodCommodityList, false);
        bvpCommodity = (BannerViewPager) headerView.findViewById(R.id.bvp_classification_commodity);
        rvNewCommodityArea = (RecyclerView) headerView.findViewById(R.id.rv_new_commodity_area);
        adapter.addHeaderView(headerView);
        //新品专场
        newCommodityAdapter = new HotAdapter(getContext());

        rvNewCommodityArea.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvNewCommodityArea.setNestedScrollingEnabled(false);
        rvNewCommodityArea.setAdapter(newCommodityAdapter);
        newCommodityAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(newCommodityAdapter.getItem(position).title);
            }
        });

    }

    @Override
    protected void initViewAndListener() {

        presenter.loadCommodityBannerData();
        presenter.loadNewCommodityData();
        presenter.loadGoodCommodityData();

    }

    @Override
    public void updateCommodityBannerData(List<BannerModel> bannerModelList) {
        bannerAdapter = new BannerAdapter<>(getContext(), bannerModelList, 1);
        bvpCommodity.setAutoRollingTime(1000 * 3);
        bvpCommodity.setAdapter(bannerAdapter);
    }

    @Override
    public void updateNewCommodityData(List<CommodityTypeModel> goodsTypeList) {
        newCommodityAdapter.updateData(goodsTypeList);
    }

    @Override
    public void updateGoodCommodityData(List<CommodityTypeModel> goodsTypeList) {
        if (goodsTypeList != null) {
            this.goodsTypeList.addAll(goodsTypeList);
            adapter.notifyDataSetChanged();
        }
    }


}
