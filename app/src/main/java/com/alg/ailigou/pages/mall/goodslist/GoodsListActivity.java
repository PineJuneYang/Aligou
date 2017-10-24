package com.alg.ailigou.pages.mall.goodslist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.mall.goodslist.adapter.GoodsListFragmentAdapter;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 玖泞
 * on 2017/7/22
 * 此类或接口用于
 */

public class GoodsListActivity extends BaseMvpActivity implements GoodsListContracts.View {

    @Inject
    GoodsListPresenter presenter;

    @BindView(R.id.tab_layout_goods_list)
    TabLayout tabLayoutGoodsList;
    @BindView(R.id.viewPager_goods_list)
    ViewPager viewPagerGoodsList;
    @BindView(R.id.tv_goods_list_title)
    TextView tvGoodsListTitle;

    private List<String> tabLayoutTitles = new ArrayList<>();
    private GoodsListFragmentAdapter goodsListFragmentAdapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
//        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }


    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_goods_list;
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();
        presenter.loadTabTitles();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();


        goodsListFragmentAdapter = new GoodsListFragmentAdapter(getSupportFragmentManager(), tabLayoutTitles);
        WidgetUtils.setTabAsTvWidth(tabLayoutGoodsList, 5, 5);//设置指示器的长度
        tabLayoutGoodsList.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayoutGoodsList.setupWithViewPager(viewPagerGoodsList);
        // TODO: 2017/7/21
        viewPagerGoodsList.setAdapter(goodsListFragmentAdapter);

    }

    @Override
    public void updateTablayoutTitle(List<String> tabTitles) {
        this.tabLayoutTitles = tabTitles;
    }



}
