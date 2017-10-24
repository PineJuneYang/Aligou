package com.alg.ailigou.pages.personal.seelogistics;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.seelogistics.adapter.LogisticsAdapter;
import com.alg.ailigou.pages.personal.seelogistics.adapter.OrderGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于 查看物流
 */

public class SeeLogisticsActivity extends BaseMvpActivity implements SeeLogisticsContracts.View {
    @Inject
    SeeLogisticsPresenter mPresenter;
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
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.iv_logistics)
    ImageView mIvLogistics;
    @BindView(R.id.tv_logistics_name)
    TextView mTvLogisticsName;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_statu)
    TextView mTvStatu;
    @BindView(R.id.recyler_view_goods)
    RecyclerView mRecylerViewGoods;
    @BindView(R.id.recyler_view_logistics)
    RecyclerView mRecylerViewLogistics;
    private LogisticsAdapter adpterLogistics;//物流的adapter
    private OrderGoodsAdapter adapterGoods;//商品的adapter
    private List<LogisticsModel.TracesBean> logistics;
    private List<CommodityModel> mCommodityModels;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void initBase() {
        super.initBase();
        mCommodityModels = new ArrayList<>();
        logistics = new ArrayList<>();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("查看物流");
        initRecylerview();

    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

    }

    private void initRecylerview() {
        adpterLogistics = new LogisticsAdapter(logistics, this);
        adapterGoods = new OrderGoodsAdapter(mCommodityModels, this);
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
        mLinearLayoutManager1.setSmoothScrollbarEnabled(true);
        mLinearLayoutManager1.setAutoMeasureEnabled(true);
        mRecylerViewGoods.setNestedScrollingEnabled(false);
        mRecylerViewGoods.setLayoutManager(mLinearLayoutManager1);


        LinearLayoutManager mLinearLayoutManager2 = new LinearLayoutManager(this);
        mLinearLayoutManager2.setSmoothScrollbarEnabled(true);
        mLinearLayoutManager2.setAutoMeasureEnabled(true);
        mRecylerViewLogistics.setNestedScrollingEnabled(false);
        mRecylerViewLogistics.setLayoutManager(mLinearLayoutManager2);

        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) mRecylerViewGoods.getItemAnimator()).setSupportsChangeAnimations(false);
        ((SimpleItemAnimator) mRecylerViewLogistics.getItemAnimator()).setSupportsChangeAnimations(false);

        mRecylerViewGoods.setAdapter(adapterGoods);
        mRecylerViewLogistics.setAdapter(adpterLogistics);

        mPresenter.loadLogisticsData("YD","1202516745301");
    }


    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_see_logistics;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void goodsListNotify(Object data) {
        mCommodityModels.addAll(CartList.getGood2s());
        adapterGoods.notifyDataSetChanged();
    }

    @Override
    public void logisticsListNotify(LogisticsModel model) {
        logistics.clear();
        logistics.addAll(model.Traces);
        adpterLogistics.notifyDataSetChanged();
        ImageLoadUtils.load(this,model.Icon,mIvLogistics);
        mTvLogisticsName.setText(model.ShipperCode);
        mTvNumber.setText(model.LogisticCode);
       // 2-在途中,3-签收,4-问题件
        switch (model.State){
            case 2:
                mTvStatu.setText("在途中");
                break;
            case 3:
                mTvStatu.setText("签收");
                break;
            case 4:
                mTvStatu.setText("问题件");
                break;
        }
    }
}
