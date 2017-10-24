package com.alg.ailigou.pages.personal.mycollection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.mycollection.adapter.MyCollectionFragmentAdapter;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于 我的收藏界面
 */

public class MyCollectionActivity extends BaseMvpActivity implements MyCollectionContracts.View {


    @Inject
    MyCollectionPresenter presenter;
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
    @BindView(R.id.tab_layout_my_collection)
    TabLayout tabLayoutMyCollection;
    @BindView(R.id.viewPager_my_collection)
    ViewPager viewPagerMyCollection;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;

    private List<String> tabLayoutTitles = new ArrayList<>();
    private boolean isEdit = true;


    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();

        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        tvBaseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        tvBaseTitle.setTextColor(getResources().getColor(R.color.alg_common_37));
        ivBaseBack.setBackgroundResource(R.drawable.alg_home_commidity_detail_arrow);

        tvBaseTitle.setText("我的收藏");
        tvBaseEdit.setText("编辑");
        tvBaseEdit.setTextColor(getResources().getColor(R.color.alg_common_37));
        llBaseEdit.setVisibility(View.VISIBLE);
        tvBaseEdit.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();


//        goodsListFragmentAdapter = new GoodsListFragmentAdapter(getSupportFragmentManager(), tabLayoutTitles);
//        WidgetUtils.setTabAsTvWidth(tabLayoutGoodsList, 5, 5);//设置指示器的长度
//        tabLayoutGoodsList.setTabGravity(TabLayout.MODE_SCROLLABLE);
//        tabLayoutGoodsList.setupWithViewPager(viewPagerGoodsList);
//        // TODO: 2017/7/21
//        viewPagerGoodsList.setAdapter(goodsListFragmentAdapter);
        presenter.loadTitle();

        MyCollectionFragmentAdapter fragmentAdapter = new MyCollectionFragmentAdapter(getSupportFragmentManager(), tabLayoutTitles);
        WidgetUtils.setTabAsTvWidth(tabLayoutMyCollection, 5, 5);//设置指示器的长度
        tabLayoutMyCollection.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayoutMyCollection.setupWithViewPager(viewPagerMyCollection);
        viewPagerMyCollection.setAdapter(fragmentAdapter);
        viewPagerMyCollection.setOffscreenPageLimit(2);
        viewPagerMyCollection.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    if (tvBaseEdit.getText().toString().equals("编辑")){

                        tvBaseEdit.setText("编辑");
                    }else {
                        tvBaseEdit.setText("完成");
                    }


                } else {
                    tvBaseEdit.setText("清空");
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_collection;
    }


    @Override
    public void updateTitles(List<String> titles) {
        this.tabLayoutTitles = titles;
    }


    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }


    @Override
    protected boolean hasBus() {
        return true;
    }


    @OnClick({R.id.ll_base_edit, R.id.ll_base_back})
    public void onViewClicked(View view) {
        //rxbus发消息
        switch (view.getId()) {

            case R.id.ll_base_edit:
                if (tvBaseEdit.getText().toString().equals("编辑")) {
                    tvBaseEdit.setText("完成");
                    RxBus.get().post("MyCollectionActivity", "编辑");
                } else if (tvBaseEdit.getText().toString().equals("完成")){
                    tvBaseEdit.setText("编辑");
                    RxBus.get().post("MyCollectionActivity", "完成");
                }else if (tvBaseEdit.getText().toString().equals("清空")){
                    RxBus.get().post("MyCollectionActivity", "清空");
                }
                break;


            case R.id.ll_base_back:
                finish();
                break;

        }
    }


}
