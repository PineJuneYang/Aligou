package com.alg.ailigou.pages.home.self;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomToolBar;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.home.self.adapter.SelfClassicAdapter;
import com.alg.ailigou.pages.home.self.adapter.SelfHotAdapter;
import com.alg.ailigou.pages.home.self.adapter.SelfMonthActionAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self
 * Created by Chris Chen on 2017/7/13 13:58.
 * Explain:自营
 */

public class SelfActivityOld extends BaseMvpActivity implements SelfContracts.View {
    @BindView(R.id.ctb_self_top)
    CustomToolBar toolBar;//工具条
    @BindView(R.id.bvp_self_banner)
    BannerViewPager bvpSelfBanner;
    @BindView(R.id.rv_self_classic)
    CustomRecyclerView rvSelfClassic;
    @BindView(R.id.rv_self_hot)
    CustomRecyclerView rvSelfHot;
    @BindView(R.id.rv_self_month_action)
    CustomRecyclerView rvSelfMonthAction;

    @Inject
    SelfPresenter presenter;

    SelfClassicAdapter selfClassicAdapter;
    SelfHotAdapter selfHotAdapter;
    SelfMonthActionAdapter selfMonthActionAdapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_self;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
       // DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        //初始化工具条
        setSupportActionBar(toolBar.getToolBar());
        toolBar.setTitle("自营");
//        toolBar.getToolBar().setNavigationIcon(R.mipmap.alg_ic_launcher);
        toolBar.setActivity(this);
        toolBar.setBackIcon(R.mipmap.alg_common_icon_arrow_back);
        //分类
        selfClassicAdapter = new SelfClassicAdapter(this);
        rvSelfClassic.setLayoutManager(new GridLayoutManager(this, 4));
        rvSelfClassic.setAdapter(selfClassicAdapter);
        //热销商品
        selfHotAdapter = new SelfHotAdapter(this);
        rvSelfHot.setLayoutManager(new GridLayoutManager(this, 2));
        rvSelfHot.setAdapter(selfHotAdapter);
        //本月活动
        selfMonthActionAdapter = new SelfMonthActionAdapter(this);
        rvSelfMonthAction.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSelfMonthAction.setAdapter(selfMonthActionAdapter);
        selfMonthActionAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug("抢购："+selfMonthActionAdapter.getItem(position).title);
            }
        });



        //加载数据
//        presenter.loadBannerData();
//        presenter.loadClassicData();
//        presenter.loadHotData();
//        presenter.loadMonthActionData();
    }



    @Override
    public void undateSelfData(SelfGoodsDataModel model) {

    }

    @Override
    public void undateCommendData(PageModel<CommodityModel> commodityModelPageModel) {

    }

//    @Override
//    public void updateBannerData(List<BannerModel> bannerModelList) {
//        BannerAdapter<BannerModel> bannerAdapter = new BannerAdapter<>(this, bannerModelList, 1);
//        bvpSelfBanner.setAdapter(bannerAdapter);
//    }
//
//    @Override
//    public void updateClassicData(List<HomeCommodityTypeModel> commodityTypeModelList) {
//        selfClassicAdapter.updateData(commodityTypeModelList);
//    }
//
//    @Override
//    public void updateHotData(List<CommodityModel> commodityModelList) {
//        selfHotAdapter.updateData(commodityModelList);
//    }
//
//    @Override
//    public void updateMonthActionData(List<CommodityModel> commodityModelList) {
//        selfMonthActionAdapter.updateData(commodityModelList);
//    }
}
