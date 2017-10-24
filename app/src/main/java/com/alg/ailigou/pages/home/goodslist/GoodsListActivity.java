package com.alg.ailigou.pages.home.goodslist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.mall.goodslist.adapter.GoodsListFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/7/28
 * 此类或接口用于 商品列表页面
 */

public class GoodsListActivity extends BaseMvpActivity implements GoodsListContracts.View {


    @Inject
    GoodsListPresenter presenter;

    @BindView(R.id.tab_layout_goods_list)
    TabLayout tabLayoutGoodsList;
    @BindView(R.id.viewPager_goods_list)
    ViewPager viewPagerGoodsList;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;


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
//        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_goods_list;
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
    public void updateTitles(List<String> titles) {
        if (titles.size() > 0 && titles != null) {
            tabLayoutTitles.addAll(titles);
        }
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }



    @OnClick({R.id.ll_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ll_base_back:
                finish();
                break;


        }

    }
}
