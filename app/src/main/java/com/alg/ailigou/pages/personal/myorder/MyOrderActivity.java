package com.alg.ailigou.pages.personal.myorder;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.myorder.adapter.MyOrderFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于我的订单界面
 */

public class MyOrderActivity extends BaseMvpActivity implements MyOrderContracts.View {


    @Inject
    MyOrderPresenter presenter;
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
    @BindView(R.id.tab_layout_my_order)
    TabLayout tabLayoutMyOrder;
    @BindView(R.id.viewPager_my_order)
    ViewPager viewPagerMyOrder;

    private List<String> titles = new ArrayList<>();
    private int type;

    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvBaseTitle.setText("我的订单");

        //为TabLayout添加分割线
//        LinearLayout linearLayout = (LinearLayout) tabLayoutMyOrder.getChildAt(0);
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
//                R.drawable.alg_layout_divider_vertical));

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        presenter.loadTitle();

        MyOrderFragmentAdapter fragmentAdapter = new MyOrderFragmentAdapter(getSupportFragmentManager(), titles);
        WidgetUtils.setTabAsTvWidth(tabLayoutMyOrder, 5, 5);//设置指示器的长度
        tabLayoutMyOrder.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayoutMyOrder.setupWithViewPager(viewPagerMyOrder);
        viewPagerMyOrder.setAdapter(fragmentAdapter);
//        viewPagerMyOrder.setOffscreenPageLimit(5);

//        switch (type) {
//            case PersonalConsts.WAIT_PAR_MONEY:
//
//                viewPagerMyOrder.setCurrentItem(1);
//
//                break;
//
//            case PersonalConsts.WAIT_SEND_GOODS:
//                viewPagerMyOrder.setCurrentItem(2);
//                break;
//
//            case PersonalConsts.WAIT_RECEIVE_GOODS:
//                viewPagerMyOrder.setCurrentItem(3);
//                break;
//            case PersonalConsts.WAIT_COMMENT:
//                viewPagerMyOrder.setCurrentItem(4);
//                break;
//
//            default:
//                viewPagerMyOrder.setCurrentItem(0);
//
//                break;
//
//        }


    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_order;
    }

    @Override
    public void updateTitles(List<String> titles) {
        this.titles = titles;
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }
}
