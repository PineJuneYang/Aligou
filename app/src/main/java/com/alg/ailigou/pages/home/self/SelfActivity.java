package com.alg.ailigou.pages.home.self;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.hotrecommend.HotRecommendActivity;
import com.alg.ailigou.pages.home.hotsalelist.HotSaleListActivity;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.self.adapter.SelfAdapter;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.limit.LimitActivity;
import com.alg.ailigou.pages.mall.weeknew.WeekNewActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class SelfActivity extends BaseMvpActivity implements SelfContracts.View {
    @Inject
    SelfPresenter presenter;
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
    RefreshFrameLayout mStoreHousePtrFrame;
    private List<CommodityModel> goods;
    private SelfAdapter mAdapter;
    private View mFlateView;

    //人气推荐的请求的当前页码,
    private int page = 1;
    //人气推荐每页请求的数据
    private int pageSize = 10;

    private List<BannerModel> newsBannerList=new ArrayList<>();

    private BannerViewPager viewPger;
    private BannerAdapter bannerAdapter;
    private boolean hasNext;

    @Override

    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
        goods = new ArrayList<>();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_recylerview_width_title;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setText("自营");
        mAdapter = new SelfAdapter(goods, this);

        mRecylerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecylerView.setAdapter(mAdapter);
        mFlateView = LayoutInflater.from(this).inflate(R.layout.alg_item_home_self_header, mRecylerView, false);
        mAdapter.addHeaderView(mFlateView);

        mAdapter.setLoadMoreListenter(mRecylerView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (hasNext){
                    presenter.loadCommendData(1,page,pageSize);
                }
            }
        });

        initBanner();

        //加载头布局的数据
        presenter.loadSelfData();
        //加载推荐数据
        presenter.loadCommendData(1, page, pageSize);
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mStoreHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                frame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {

//                        mStoreHousePtrFrame.refreshComplete();
//                    }
//                }, 1800);
                //加载头布局的数据
                presenter.loadSelfData();

                goods.clear();
                page = 1;
                presenter.loadCommendData(1,page,pageSize);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(SelfActivity.this, CommodityDetailsActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_self_head_title_1:
                        startActivity(HotSaleListActivity.class);
                        break;
                    case R.id.ll_self_head_title_2:
                        startActivity(WeekNewActivity.class);
                        break;
                    case R.id.ll_self_head_title_3:
                        startActivity(HotRecommendActivity.class);
                        break;
                    case R.id.ll_self_head_title_4:
                        startActivity(LimitActivity.class);
                        break;
                    case R.id.iv_banner:
                        break;
                }
            }
        });
    }

    @Override
    public void undateSelfData(SelfGoodsDataModel model) {
        if (model!=null&&model.mNewsBannerModels.size()>0){
            ImageView iv = (ImageView) mFlateView.findViewById(R.id.iv_banner);
            Glide.with(this).load(model.banner.image).into(iv);
            bannerAdapter.setDataChange(model.mNewsBannerModels);
        }

    }

    @Override
    public void undateCommendData(PageModel<CommodityModel> model) {
        if (model.dataList != null) {
            goods.addAll(model.dataList);
            hasNext = model.hasNext;
            page++;
            mAdapter.notifyDataSetChanged();
            mAdapter.setLoading(false);
        }

    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void refreshComplete() {
        super.refreshComplete();
        RefreshCompleteUtils.refreshComplete(mStoreHousePtrFrame);
    }

    public void initBanner(){
        //设置头布局
        viewPger = (BannerViewPager) mFlateView.findViewById(R.id.bannerview);
        bannerAdapter = new BannerAdapter(this,newsBannerList, 1);
        viewPger.setAutoRollingTime(1000 * 3);
        viewPger.setAdapter(bannerAdapter);
    }

}
