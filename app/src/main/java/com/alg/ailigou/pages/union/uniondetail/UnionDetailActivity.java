package com.alg.ailigou.pages.union.uniondetail;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.utils.WidgetUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.union.consts.UniconConsts;
import com.alg.ailigou.pages.union.inject.DaggerUnionComponent;
import com.alg.ailigou.pages.union.inject.UnionModule;
import com.alg.ailigou.pages.union.uniondetail.adapter.UnionPagerAdapter;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UnionDetailActivity extends BaseMvpActivity implements UnionDetailContracts.View {
    @Inject
    UnionDetailPresenter mPresenter;
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
    @BindView(R.id.iv_unicon)
    ImageView mIvUnicon;
    @BindView(R.id.tv_unicon_name)
    TextView mTvUniconName;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;


    private UnionModel mUnionModel;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    UnionPagerAdapter mAdapter;

    @Override
    protected void initBase() {
        super.initBase();

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_union_detail;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerUnionComponent.builder().unionModule(new UnionModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("商家详情");
        mAdapter = new UnionPagerAdapter(getSupportFragmentManager(), UniconConsts.titles);
        WidgetUtils.setTabAsTvWidth(mTabLayout, 10, 10);//设置指示器的长度
        mTabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mVpContent);
        mVpContent.setAdapter(mAdapter);
        mVpContent.setOffscreenPageLimit(2);
        mPresenter.loadData(1375);
    }

    @OnClick({R.id.ll_base_back, R.id.iv_unicon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.iv_unicon:

                break;
        }
    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    private UnionModel unionModel;

    @Override
    public void notify(UnionModel unionModel) {
        this.unionModel = unionModel;
        if (unionModel != null) {
            RxBus.get().post("UnionDetailActivity", unionModel);
        }
    }


}
