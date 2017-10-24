package com.alg.ailigou.pages.mall.entrance;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.common.model.MallDataModel;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomToolBar;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.home.consts.HomeConsts;
import com.alg.ailigou.pages.home.entrance.adapter.BoutiqueAdapter;
import com.alg.ailigou.pages.home.entrance.adapter.FunctionAdapter;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.entrance.adapter.SimpleCommodityTypeAdapter;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.alg.ailigou.pages.mall.limit.LimitActivity;
import com.alg.ailigou.pages.mall.weeknew.WeekNewActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance
 * Created by Chris Chen on 2017/7/13 13:32.
 * Explain:商城
 */

public class MallActivity extends BaseMvpActivity implements MallContracts.View {
    @BindView(R.id.ctb_mall_top)
    CustomToolBar toolBar;//工具条
    @BindView(R.id.bvp_mall_banner_scroll)
    BannerViewPager bvpBanner;//头部轮播广告 滚动
    @BindView(R.id.iv_mall_banner_fix)
    ImageView ivBanner;//轮播广告 不滚动

    @BindView(R.id.rv_marking_classic)
    CustomRecyclerView rvMarkingClassic;//分类
    @BindView(R.id.rv_hot_brand)
    CustomRecyclerView rvHotBrand;//热销品牌
    @BindView(R.id.rv_popularity)
    CustomRecyclerView rvPopularity;//人气推荐

    @Inject
    MallPresenter presenter;

    //一下两个适配器样式相同，所以借用一下
    private FunctionAdapter markingAdapter;
    private SimpleCommodityTypeAdapter hotBrandAdapter;
    private BoutiqueAdapter popularityAdapter;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        setSupportActionBar(toolBar.getToolBar());
        toolBar.setTitle("商城");
//        toolBar.getToolBar().setNavigationIcon(R.mipmap.alg_ic_launcher);
        toolBar.setActivity(this);
        toolBar.setBackIcon(R.mipmap.alg_common_icon_arrow_back);

        initMarkingClassic();

        //初始化热销品牌
        hotBrandAdapter = new SimpleCommodityTypeAdapter(this);
        rvHotBrand.setLayoutManager(new GridLayoutManager(this, 2));
        rvHotBrand.setAdapter(hotBrandAdapter);
        hotBrandAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(hotBrandAdapter.getItem(position).title);
                startActivity(new Intent(MallActivity.this, CommodityDetailsActivity.class));
            }
        });
        //初始化人气推荐
        popularityAdapter = new BoutiqueAdapter(this);
        rvPopularity.setLayoutManager(new GridLayoutManager(this, 2));
        rvPopularity.setAdapter(popularityAdapter);
        popularityAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(popularityAdapter.getItem(position).title);

            }
        });
        //初始化轮播广告
        bvpBanner.setHasIndicator(true);
        presenter.loadData();//加载页面数据
    }

    /**
     * 初始化分类
     */
    private void initMarkingClassic() {
        final List<FunctionModel> markingList = new ArrayList<>();
        for (int i = 0; i < HomeConsts.HOME_MARKING_CLASSIC_TITLES.length; i++) {
            markingList.add(new FunctionModel(i, HomeConsts.HOME_MARKING_CLASSIC_TITLES[i], HomeConsts.HOME_MARKING_CLASSIC_ICONS[i]));
        }

        markingAdapter = new FunctionAdapter(this, markingList);
        rvMarkingClassic.setLayoutManager(new GridLayoutManager(this, 4));
        rvMarkingClassic.setAdapter(markingAdapter);
        markingAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(markingAdapter.getItem(position).title);
                switch (position) {
                    case 0://限时特卖
                        startActivity(new Intent(MallActivity.this, LimitActivity.class));
                        break;
                    case 1://本周上新
                        startActivity(new Intent(MallActivity.this, WeekNewActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void updateData(MallDataModel mallDataModel) {
        bvpBanner.setAdapter(new BannerAdapter(getBaseContext(), mallDataModel.scrollBannerData, 1));
        ImageUtils.load(this, mallDataModel.fixBannerUrl, ivBanner);
        hotBrandAdapter.updateData(mallDataModel.hotBrandList);
        popularityAdapter.updateData(mallDataModel.popularityList);
    }
}
