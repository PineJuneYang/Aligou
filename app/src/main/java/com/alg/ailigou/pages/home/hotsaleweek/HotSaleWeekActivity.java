package com.alg.ailigou.pages.home.hotsaleweek;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.hotsaleweek.adapter.HotSaleWeekPagerAdapter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于  本周热卖
 */

public class HotSaleWeekActivity extends BaseMvpActivity implements HotSaleWeekContracts.View {
    @Inject
    HotSaleWeekPresenter mPresenter;
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private List<String> mList;
    private HotSaleWeekPagerAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        //在这里请求数据
        mPresenter.loadActionBannerData();
        mPresenter.loadTabLayoutTitle();
        initViewPager();

    }

    private void initViewPager() {

        adapter = new HotSaleWeekPagerAdapter(getSupportFragmentManager(), mList);
        WidgetUtils.setTabAsTvWidth(mTabLayout, 5, 5);//设置指示器的长度
        mTabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mVpContent);
        mVpContent.setAdapter(adapter);
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }



    @Override
    protected int layoutId() {
        return R.layout.alg_act_commom_coordinatorlayout;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void updateTopImageViewData(HomeHotSaleCommodityModel homeHotSaleCommodityModelPageModel) {

    }

    @Override
    public void updateTabLayoutTitle(List<String> titles) {
        this.mList = titles;
    }
}
