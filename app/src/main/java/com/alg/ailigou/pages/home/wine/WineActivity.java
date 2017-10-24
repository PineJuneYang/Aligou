package com.alg.ailigou.pages.home.wine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.HomeWineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.constans.CartConstant;
import com.alg.ailigou.pages.cart.entrance.CartFragment;
import com.alg.ailigou.pages.home.goodslist.GoodsListActivity;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.wine.adapter.WineAdapter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.wine
 * Created by Chris Chen on 2017/7/13 14:02.
 * Explain:佳酿
 */

public class WineActivity extends BaseMvpActivity implements WineContracts.View {
    @Inject
    WinePresenter presenter;
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
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout mStoreHousePtrFrame;
    private WineAdapter topBannerAdapter;//最顶部的banner
    private WineAdapter horizontalAdapter;//s水平的recylerview
    private WineAdapter normalBannerAdapter;//正常只有图片的banner
    private WineAdapter verticalAdapter;//两列的recylerview
    private VirtualLayoutManager layoutManager;
    private List<CommodityModel> topBannerDatas;//最顶部的banner数据
    private List<CommodityTypeModel> horizontalDatas;//水平的recylerview数据
    private List<BannerModel> normalBannerDatas;//正常只有图片的banner的数据
    private List<CommodityModel> verticalDatas;//两列的recylerview数据
    private boolean hasNext = true;
    private DelegateAdapter mDelegateAdapter;
    private List<String>  recommendTitles = new ArrayList<>();
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_wine;
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void initBase() {
        super.initBase();
        topBannerDatas = new ArrayList<>();
        horizontalDatas = new ArrayList<>();
        normalBannerDatas = new ArrayList<>();
        verticalDatas = new ArrayList<>();
//        verticalDatas.addAll(CartList.getGoods());
        //// TODO: 2017/8/28  给"推荐您喜欢"模拟一个长度为一的数组,方便adapter的getCount等于1
        recommendTitles.add("000");
    }

    private void initData() {
        presenter.loadWineData();
        presenter.loadRecommendGoodsData(0, 1, 20);
        //// TODO: 2017/8/28 模拟的假数据
        verticalDatas.addAll(CartList.getGood2s());
        verticalAdapter.notifyDataSetChanged();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("佳酿");
    }

    @Override
    protected void initViewAndListener() {

        super.initViewAndListener();
        mLlBaseEdit.setVisibility(View.GONE);
        mLlBaseNotice.setVisibility(View.GONE);
        mTvBaseTitle.setText("商城");
        initRecylerView();
        mStoreHousePtrFrame.disableWhenHorizontalMove(true);
        mStoreHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStoreHousePtrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    //初始化recylerView
    private void initRecylerView() {
        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) mRecylerView.getItemAnimator()).setSupportsChangeAnimations(false);

        layoutManager = new VirtualLayoutManager(this);
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
        mRecylerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecylerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 15);
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        topBannerAdapter = new WineAdapter(topBannerDatas, this, null, BaseVLayoutAdapter.BASE_ITEM_TYPE_BANNER_WIDTH_BG);
        horizontalAdapter = new WineAdapter(horizontalDatas, this, null, BaseVLayoutAdapter.BASE_ITEM_TYPE_HORIZONTAL);
        normalBannerAdapter = new WineAdapter(normalBannerDatas, this, null, BaseVLayoutAdapter.BASE_ITEM_TYPE_HEADER_BANNER);
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setAutoExpand(false);//当一行里视图的个数少于spanCount值的时候，如果autoExpand为true，视图的总宽度会填满可用区域；否则会在屏幕上留空白区域。
        verticalAdapter = new WineAdapter(verticalDatas, this, helper, BaseVLayoutAdapter.BASE_ITEM_TYPE_RECOMMEND);

        adapters.add(topBannerAdapter);
        adapters.add(horizontalAdapter);
        adapters.add(normalBannerAdapter);
        adapters.add(new WineAdapter(recommendTitles, this, null, BaseVLayoutAdapter.BASE_ITEM_TYPE_HEADER_TITLE));
        adapters.add(verticalAdapter);

        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        mDelegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        mDelegateAdapter.setAdapters(adapters);
        // 5. 将delegateAdapter绑定到recyclerView
        mRecylerView.setAdapter(mDelegateAdapter);
        horizontalAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                startActivityWithExtra(GoodsListActivity.class, "CommodityTypeModel", horizontalDatas.get(position));
            }
        });
        verticalAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });
        //banner的点击事件
        topBannerAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {

            }
        });
        initData();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }


    @Override
    public void wineDataNotifity(HomeWineDataModel model) {

        if (model != null) {
            if (model.wineBannerList != null) {
                topBannerDatas.addAll(model.wineBannerList);
                topBannerAdapter.notifyDataSetChanged();
            }
            if (model.wineSeriesList != null) {
                horizontalDatas.addAll(model.wineSeriesList);
                horizontalAdapter.notifyDataSetChanged();
            }
            if (model.sportBannerList != null) {
                normalBannerDatas.addAll(model.sportBannerList);
                normalBannerAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void recommendGoodsNotifity(PageModel<CommodityModel> commodityModel) {
        if (commodityModel.dataList != null) {
            hasNext = commodityModel.hasNext;
            pager = pager++;
            verticalDatas.addAll(commodityModel.dataList);
            verticalAdapter.notifyDataSetChanged();
        }

    }



    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }
}
