package com.alg.ailigou.pages.home.ligoupaper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeLigouPaperDataModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.ligouchangenotes.LigouChangeNotesActivity;
import com.alg.ailigou.pages.home.ligouoverage.LigouOverageActivity;
import com.alg.ailigou.pages.home.ligoupaper.adapter.ExchangeGoodsAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:50.
 * Explain:利购券
 */

public class LigouPaperActivity extends BaseMvpActivity implements LigouPaperContracts.View {
    @Inject
    LigouPaperPresenter presenter;
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
    private ExchangeGoodsAdapter mAdapter;
    private List<CommodityModel> goods = new ArrayList<>();
    private View mView;//头布局
    private boolean hasNext;
    private int pager = 0;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_ligoupaper;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }
    @Override
    protected void afterContentView() {
        super.afterContentView();
        mLlBaseNotice.setVisibility(View.GONE);
        mLlBaseEdit.setVisibility(View.GONE);
        mTvBaseTitle.setText("利购券兑换商城");
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mAdapter = new ExchangeGoodsAdapter(goods, this);
        mRecylerView.setAdapter(mAdapter);
        mView = LayoutInflater.from(this).inflate(R.layout.alg_item_home_ligoupaper_head, mRecylerView, false);
        mAdapter.addHeaderView(mView);
        mAdapter.notifyDataSetChanged();
        presenter.loadData(pager, 20);
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mAdapter.setOnOnExchangeClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                //立即兑换
            }
        });
        //条目点击事件
        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });
        //利购券兑换余额
        mView.findViewById(R.id.ll_cheap_ticket_overage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LigouOverageActivity.class);
            }
        });
        //兑换记录
        mView.findViewById(R.id.ll_exchange_notes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LigouChangeNotesActivity.class);
            }
        });
    }


    @Override
    public void notify(HomeLigouPaperDataModel dataModel) {

        if (dataModel.mCommodityModelPageModel != null) {
            hasNext = dataModel.mCommodityModelPageModel.hasNext;
            pager++;
            goods.addAll(dataModel.mCommodityModelPageModel.dataList);
        }
        ImageView iv_banner = (ImageView) mView.findViewById(R.id.iv_banner);
        TextView tv_cheap_ticket_overage = (TextView) mView.findViewById(R.id.tv_cheap_ticket_overage);
        TextView tv_exchange_notes = (TextView) mView.findViewById(R.id.tv_exchange_notes);
        Glide.with(this).load(dataModel.banner.image).into(iv_banner);
        tv_cheap_ticket_overage.setText(dataModel.overage+"");//余额
        tv_exchange_notes.setText(dataModel.exChangeCount+"");//兑换记录
        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/7/27
            }
        });
        mAdapter.notifyDataSetChanged();
    }
}
