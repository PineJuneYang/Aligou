package com.alg.ailigou.pages.home.entrance.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.BaseUiViewHolder;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.library.widget.banner.BannerViewPager;

import butterknife.BindView;

import static com.alg.ailigou.R.id.tv_home_top_bar_location_city;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.holder
 * Created by Chris Chen on 2017/8/14 10:28.
 * Explain:首页UI持有者
 */

public class HomeUiViewHolder extends BaseUiViewHolder {
    @BindView(tv_home_top_bar_location_city)
    public TextView tvHomeTopBarLocationCity;//定位城市
    @BindView(R.id.iv_home_scan)
    public ImageView ivHomeNotice;//消息
    @BindView(R.id.sv_home_container)
    public CustomScrollView svHomeContainer;//首页滚动

    @BindView(R.id.bvp_home_news_banner)
    public BannerViewPager bvpNewsBanner;// 新闻轮播
    @BindView(R.id.rv_home_function)
    public RecyclerView rvFunction;//功能入口
    @BindView(R.id.vf_home_quick_news)
    public ViewFlipper vfQuickNews;//爱利购快讯

    @BindView(R.id.tv_limit_into_time)
    public TextView tvLimitIntoTime;//限时抢购：入场时间
    @BindView(R.id.tv_limit_count_down_hour)
    public TextView tvLimitCountDownHour;//限时抢购：倒计时：时
    @BindView(R.id.tv_limit_count_down_minute)
    public TextView tvLimitCountDownMinute;//限时抢购：倒计时：分
    @BindView(R.id.tv_limit_count_down_second)
    public TextView tvLimitCountDownSecond;//限时抢购：倒计时：秒

    @BindView(R.id.rv_limit_goods_list)
    public RecyclerView rvLimitGoods;//限时抢购：商品列表

    @BindView(R.id.iv_home_banner_luck_rotate)
    public ImageView ivLuckRotate;//幸运大转盘
    @BindView(R.id.bvp_home_action_banner)
    public BannerViewPager bvpActionBanner;//活动轮播
    @BindView(R.id.rv_home_boutique)
    public RecyclerView rvBoutique;//精品推荐
    @BindView(R.id.iv_home_union_banner)
    public ImageView ivUnionBanner;//联盟商家宣传图
    @BindView(R.id.rv_home_union_list)
    public CustomRecyclerView rvUnionBusiness;//联盟商家列表

    public HomeUiViewHolder(View contentView) {
        super(contentView);
    }
}
