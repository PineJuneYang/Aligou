package com.alg.ailigou.mock.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.widget.banner.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/11.
 * 此类或接口用于
 */

public class TestActivity extends BaseMvpActivity {

    @BindView(R.id.bannerview)
    BannerViewPager mBannerview;
    List<View> urls;
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    private BannerAdapter<BannerModel> mAdapter;


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_debug_banner;
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        urls = new ArrayList<>();
//        Glide.with(this).load(R.drawable.sdss).
//                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(mIv, 5));
        View view = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view);
        View view2 = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view3);
        mAdapter = new BannerAdapter<>(this, urls, 1);
        mBannerview.setHasIndicator(true);
        mBannerview.setIndicatorNoSelectColor(getResources().getColor(R.color.alg_indicator_noselect_color));
        mBannerview.setIndicatorSelectColor(getResources().getColor(R.color.alg_indicator_selected_color));
        mBannerview.setAutoRollingTime(1000 * 3);
        mBannerview.setAdapter(mAdapter);
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        urls.clear();
        View view = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view);
        View view2 = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.alg_item_home_week_new, mBannerview, false);
        urls.add(view3);
        mAdapter.setDataChange(urls);
    }
}
