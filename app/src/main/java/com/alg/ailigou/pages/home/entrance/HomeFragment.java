package com.alg.ailigou.pages.home.entrance;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.event.UnionEvent;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.model.LimitBlockModel;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.common.widget.QuickNewsItemView;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.event.BaseEvent;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.library.widget.banner.ViewPagerAdapter;
import com.alg.ailigou.pages.home.entrance.adapter.BoutiqueAdapter;
import com.alg.ailigou.pages.home.entrance.adapter.BusinessAdapter;
import com.alg.ailigou.pages.home.entrance.adapter.FunctionAdapter;
import com.alg.ailigou.pages.home.entrance.adapter.HomeLimitAdapter;
import com.alg.ailigou.pages.home.entrance.holder.HomeUiViewHolder;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.ligoupaper.LigouPaperActivity;
import com.alg.ailigou.pages.home.make.ScanActivity;
import com.alg.ailigou.pages.home.newgoods.NewGoodsActivity;
import com.alg.ailigou.pages.home.searchgoods.SearchGoodsActivity;
import com.alg.ailigou.pages.home.self.SelfActivity;
import com.alg.ailigou.pages.home.utils.CountDownTimer;
import com.alg.ailigou.pages.home.utils.HomeUtils;
import com.alg.ailigou.pages.home.utils.LimitTimeModel;
import com.alg.ailigou.pages.home.utils.MyCountDownTimer;
import com.alg.ailigou.pages.home.wine.WineActivity;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.news.entrance.NewsActivity;
import com.alg.ailigou.pages.union.uniondetail.UnionDetailActivity;
import com.alg.ailigou.selectcity.CitySelectActivity;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.RefreshFrameLayout;

import static com.alg.ailigou.R.id.tv_home_top_bar_location_city;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance
 * Created by Chris Chen on 2017/7/4 15:29.
 * Explain:首页MVP视图处理
 */

public class HomeFragment extends BaseMvpFragment implements HomeContrats.View, IntentKeys {
    @Inject
    HomePresenter presenter;

    private HomeUiViewHolder viewHolder;

    private FunctionAdapter functionAdapter;
    private BoutiqueAdapter mBoutiqueAdapter;
    private HomeLimitAdapter homeLimitAdapter;
    private BusinessAdapter businessAdapter;
    private MyCountDownTimer myCountDownTimer;
    private List<BannerModel> actionBanners=new ArrayList<>();
    private List<BannerModel> newsBannerList=new ArrayList<>();
    @BindView(R.id.tv_home_top_bar_location_city)
    TextView tvLocation;
    @BindView(R.id.refresh_view)
    RefreshFrameLayout mRefreshFrameLayout;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_home;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void createViewHolder(View contentView) {
        this.viewHolder = new HomeUiViewHolder(contentView);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvLocation.setText("正在定位...");
        //监听滚动
        viewHolder.svHomeContainer.setOnScrollToBottomLintener(new CustomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom) {
                    presenter.loadMoreUnionData();
                }
            }
        });
        setAdapterAndistener();
        presenter.loadFunction();//初始化功能模块入口
        presenter.loadData();
        refreshData();

    }

    private void setAdapterAndistener() {
        //初始化限时抢购
        homeLimitAdapter = new HomeLimitAdapter(getContext());
        viewHolder.rvLimitGoods.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewHolder.rvLimitGoods.setNestedScrollingEnabled(false);
        viewHolder.rvLimitGoods.setAdapter(homeLimitAdapter);
        homeLimitAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(homeLimitAdapter.getItem(position).title);
            }
        });
        //初始化精品推荐
        mBoutiqueAdapter = new BoutiqueAdapter(getContext());
        viewHolder.rvBoutique.setLayoutManager(new GridLayoutManager(getContext(), 2));
        viewHolder.rvBoutique.setNestedScrollingEnabled(false);
        viewHolder.rvBoutique.setAdapter(mBoutiqueAdapter);
        mBoutiqueAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(mBoutiqueAdapter.getItem(position).title);
                Intent intent = new Intent(getContext(), CommodityDetailsActivity.class);
                intent.putExtra(GOODS_ID, mBoutiqueAdapter.getItem(position).id);
                startActivity(intent);
            }
        });
        //初始化联盟商家
        businessAdapter = new BusinessAdapter(getContext());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        viewHolder.rvUnionBusiness.setLayoutManager(linearLayoutManager);
        viewHolder.rvUnionBusiness.setAdapter(businessAdapter);
        businessAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
//                MessageUtils.debug(businessAdapter.getItem(position).storeName);
                Intent intent = new Intent(getContext(), UnionDetailActivity.class);
                intent.putExtra(GOODS_ID, businessAdapter.getItem(position).id);
                startActivity(intent);
            }
        });
        setActionBanner();
        setNewsbanner();
    }

    BannerAdapter<BannerModel> newsAdapter;

    private void setNewsbanner() {
        newsAdapter = new BannerAdapter<>(getActivity(), newsBannerList, 1);
        viewHolder.bvpNewsBanner.setHasIndicator(true);
        viewHolder.bvpNewsBanner.setIndicatorNoSelectColor(getResources().getColor(R.color.alg_indicator_noselect_color));
        viewHolder.bvpNewsBanner.setIndicatorSelectColor(getResources().getColor(R.color.alg_indicator_selected_color));
        viewHolder.bvpNewsBanner.setAutoRollingTime(1000 * 3);
        viewHolder.bvpNewsBanner.setAdapter(newsAdapter);
        newsAdapter.setOnPageClickListener(new ViewPagerAdapter.OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                MessageUtils.debug(newsBannerList.get(position).url);
            }
        });
    }

    //下拉刷新
    private void refreshData() {
        mRefreshFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.loadData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @Override
    public void updateData(HomeDataModel homeDataModel) {
        updateNewsBanner(homeDataModel.newsBannerData);
        updateQuickNews(homeDataModel.newsList);
        updateLimitData(homeDataModel.limitBlockData);
        ImageLoadUtils.load(getContext(), homeDataModel.luckUrl, viewHolder.ivLuckRotate);//幸运大转盘图片
        updateActionBanner(homeDataModel.actionBannerData);
        updateBoutiqueData(homeDataModel.boutiqueList);
        updateUnionBanner(homeDataModel.unionBanner);
        updateUnionData(homeDataModel.businessList);
    }

    /**
     * 更新新闻轮播广告
     *
     * @param newsBannerList
     */
    public void updateNewsBanner(final List<BannerModel> newsBannerList) {
        if (newsBannerList != null && newsBannerList.size() != 0) {
            newsAdapter.setDataChange(newsBannerList);
        }
    }

    /**
     * 更新功能模块数据
     *
     * @param functionModelList
     */
    public void updateFunction(List<FunctionModel> functionModelList) {
        functionAdapter = new FunctionAdapter(getContext(), functionModelList);
        viewHolder.rvFunction.setLayoutManager(new GridLayoutManager(getContext(), 5));
        viewHolder.rvFunction.setNestedScrollingEnabled(false);
        viewHolder.rvFunction.setAdapter(functionAdapter);
        functionAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                switch (position) {
                    case 0://自营
                        startActivity(SelfActivity.class);
                        break;
                    case 1://利购券
                        startActivity(LigouPaperActivity.class);
                        break;
                    case 2://新品
                        startActivity(NewGoodsActivity.class);
                        break;
                    case 3://佳酿
                        startActivity(WineActivity.class);
                        break;
                    case 4://联盟商家//跳转到一级页面：联盟商家
                        RxBus.get().post(new UnionEvent(BaseEvent.EVENT_SUCCESS));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void updateMoreUnionData(List<UnionModel> unionModelList) {
        businessAdapter.loadMore(unionModelList);
    }

    /**
     * 更新爱利购快讯
     *
     * @param quickNews
     */
    public void updateQuickNews(PageModel<NewsModel> quickNews) {
        if (quickNews.dataList != null && quickNews.dataList.size() != 0) {
            for (int i = 0; i < quickNews.dataList.size() / 2; i++) {
                viewHolder.vfQuickNews.addView(new QuickNewsItemView(getContext()).setFirstTitle(quickNews.dataList.get(2 * i).title).setSecondTitle(quickNews.dataList.get(2 * i + 1).title));
            }
        }
    }

    /**
     * 更新限时抢购板块
     *
     * @param limitBlockData
     */
    private void updateLimitData(LimitBlockModel limitBlockData) {
        if (limitBlockData != null && limitBlockData.goodsList != null && limitBlockData.goodsList.size() != 0) {
            viewHolder.tvLimitIntoTime.setText(new SimpleDateFormat("hh:mm").format(limitBlockData.intoTime) + "进场");
            homeLimitAdapter.updateData(limitBlockData.goodsList);
            //开启倒计时任务
            startCountDown(limitBlockData.intoTime, limitBlockData.nowTime);
        }
    }

    /**
     * 开始倒计时
     *
     * @param time        php返回的时间戳是10位，真实接口要注意处理
     * @param currentTime 接口需要返回服务器的当前时间
     *                    由于经常需要重启线程，所以这部分的接口需要分离出来
     */
    private void startCountDown(final long time, long currentTime) {
        long diffTimeSecond = time - currentTime;
        myCountDownTimer = new MyCountDownTimer(diffTimeSecond, 1000);
        myCountDownTimer.setOnTimeTickListener(new CountDownTimer.OnTimeTickListener() {
            @Override
            public void onTimeTike(long millisSecond) {
                LimitTimeModel limitTime = HomeUtils.getLimitTime(millisSecond);
                viewHolder.tvLimitCountDownHour.setText(limitTime.hourStr);
                viewHolder.tvLimitCountDownMinute.setText(limitTime.minuteStr);
                viewHolder.tvLimitCountDownSecond.setText(limitTime.secondStr);
            }

            @Override
            public void onFinished() {
                myCountDownTimer.cancel();
                viewHolder.tvLimitCountDownSecond.setText("00");
                MessageUtils.debug("该进场疯抢了");
            }
        });
        myCountDownTimer.start();
    }

    BannerAdapter<BannerModel> actionBanner;

    /**
     * 活动bannner
     */
    private void setActionBanner() {
        actionBanner = new BannerAdapter<>(getActivity(), actionBanners, 1);
        viewHolder.bvpActionBanner.setHasIndicator(true);
        viewHolder.bvpActionBanner.setIndicatorNoSelectColor(getResources().getColor(R.color.alg_indicator_noselect_color));
        viewHolder.bvpActionBanner.setIndicatorSelectColor(getResources().getColor(R.color.alg_indicator_selected_color));
        viewHolder.bvpActionBanner.setAutoRollingTime(1000 * 3);
        viewHolder.bvpActionBanner.setAdapter(actionBanner);
    }

    /**
     * 更新活动轮播广告
     *
     * @param actionBannerList
     */
    private void updateActionBanner(List<BannerModel> actionBannerList) {
        if (actionBannerList != null && actionBannerList.size() != 0) {
            actionBanner.setDataChange(actionBannerList, viewHolder.bvpNewsBanner);
        }
    }

    /**
     * 更新精品推荐列表
     *
     * @param boutiCommodityList
     */
    public void updateBoutiqueData(List<CommodityModel> boutiCommodityList) {
        if (boutiCommodityList != null && boutiCommodityList.size() != 0) {
            mBoutiqueAdapter.updateData(boutiCommodityList);
        }
    }

    /**
     * 更新联盟商家轮播广告
     *
     * @param unionBanner
     */
    private void updateUnionBanner(BannerModel unionBanner) {
        if (unionBanner != null) {
            ImageLoadUtils.load(getContext(), unionBanner.image, viewHolder.ivUnionBanner);
        }
    }

    /**
     * 更新联盟商家列表
     *
     * @param businessPageData
     */
    private void updateUnionData(PageModel<UnionModel> businessPageData) {
        if (businessPageData.dataList != null && businessPageData.dataList.size() != 0) {
            businessAdapter.updateData(businessPageData.dataList);
        }
    }

    /**
     * 控件点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_home_top_bar_location_city,
            R.id.vf_home_quick_news,
            R.id.iv_home_scan,
            R.id.ll_search_bar_container,
            R.id.iv_home_union_banner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_search_bar_container://搜索
                MessageUtils.debug("跳转到搜索界面");
                startActivity(SearchGoodsActivity.class);
                break;
            case tv_home_top_bar_location_city://城市选择
                MessageUtils.debug("切换到城市选择界面");
                startActivity(CitySelectActivity.class);
                break;
            case R.id.vf_home_quick_news://爱利购快讯
                MessageUtils.debug("切换到新闻资讯界面");
                startActivity(NewsActivity.class);
                break;
            case R.id.iv_home_scan://扫码
                startActivity(ScanActivity.class);
                break;
            case R.id.iv_home_union_banner://联盟商家宣传
                MessageUtils.debug("跳转到联盟商家宣传界面");
                break;

            default:
                break;
        }
    }

    /**
     * 接受定位信息
     */
    @Subscribe(
            tags = {
                    @Tag("location")
            }
    )
    public void getLocation(String city) {
        tvLocation.setText(city);
    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    /**
     * fragment 销毁取消倒计时
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void refreshComplete() {
        RefreshCompleteUtils.refreshComplete(mRefreshFrameLayout);
    }
}
