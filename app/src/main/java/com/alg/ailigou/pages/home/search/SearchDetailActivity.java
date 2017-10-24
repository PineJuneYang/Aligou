package com.alg.ailigou.pages.home.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.common.widget.SearchView;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.adapter.SearchDetailFragmentPagerAdapter;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/7/26
 * 此类或接口用于  搜索商品详情页面
 */

public class SearchDetailActivity extends BaseMvpActivity implements SearchDetailContracts.View, TextWatcher {


    @Inject
    SearchDetailPresenter mPresenter;


    @BindView(R.id.tab_layout_home_search)
    TabLayout tabLayoutHomeSearch;
    @BindView(R.id.vp_home_hot_search_content)
    ViewPager vpHomeHotSearchContent;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.sv_base_searchview)
    SearchView svBaseSearchview;
    @BindView(R.id.tv_base_setting)
    TextView tvBaseSetting;
    @BindView(R.id.ll_base_search_container)
    LinearLayout llBaseSearchContainer;


    private List<String> searchTitle = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private String searchHotWords;
    private SearchDetailFragmentPagerAdapter searchDetailFragmentPagerAdapter;

    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        searchHotWords = intent.getStringExtra("SearchHotWords");


    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }


    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_search;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        svBaseSearchview.setText(searchHotWords);
        mPresenter.loadTabLayoutTitle();
        tvBaseSetting.setText("搜索");
        tvBaseSetting.setVisibility(View.GONE);


        svBaseSearchview.addTextChangedListener(this);
        vpHomeHotSearchContent.setOffscreenPageLimit(searchTitle.size());

//        for (int i = 0; i < searchTitle.size(); i++) {
//            TabLayout.Tab tab = tabLayoutHomeSearch.newTab();
//            tab.setCustomView(PAGE_TAB_VIEWS[i]);
//            tabLayoutHomeSearch.addTab(tab, i, i == 0);
//        }

    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();


//       new HotSaleListFragmentAdapter(getSupportFragmentManager(),searchTitle)
        searchDetailFragmentPagerAdapter = new SearchDetailFragmentPagerAdapter(getSupportFragmentManager(), searchTitle, fragments, searchHotWords);
        WidgetUtils.setTabAsTvWidth(tabLayoutHomeSearch, 5, 5);//设置指示器的长度
        tabLayoutHomeSearch.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayoutHomeSearch.setupWithViewPager(vpHomeHotSearchContent);
        // TODO: 2017/7/21
        vpHomeHotSearchContent.setAdapter(searchDetailFragmentPagerAdapter);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayoutHomeSearch.getTabAt(0).setCustomView(getTabView(0));
        tabLayoutHomeSearch.getTabAt(1).setCustomView(getTabView(1));
        tabLayoutHomeSearch.getTabAt(2).setCustomView(getTabView(2));
        tabLayoutHomeSearch.getTabAt(3).setCustomView(getTabView(3));
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.alg_item_search_detail_tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.tv_home_search_detail_tab);
        txt_title.setText(searchTitle.get(position));
        return view;
    }


    @Override
    public void updateTablayoutTitle(List<String> tabTitles) {
        if (tabTitles != null && tabTitles.size() > 0) {
            searchTitle.addAll(tabTitles);
        }
    }


    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = svBaseSearchview.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            tvBaseSetting.setVisibility(View.GONE);
        } else {
            tvBaseSetting.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.iv_base_back, R.id.tv_base_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                finish();
                break;
            case R.id.tv_base_setting:
                if (TextUtils.isEmpty(svBaseSearchview.getText().toString().trim())) {

                    ToastUtils.showToast(this, "请输入需要的搜索商品名称");
                    return;
                } else {
                    RxBus.get().post("SearchDetailActivity", svBaseSearchview.getText().toString().trim());

                    searchDetailFragmentPagerAdapter.notifyDataSetChanged();
                }

                break;


        }
    }



}
