package com.alg.ailigou.pages.personal.refundsoraftersale;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.refundsdetail.RefundsDetailActivity;
import com.alg.ailigou.pages.personal.refundsoraftersale.adapter.RefundsAfterSaleRecycleAdapter;
import com.alg.ailigou.pages.personal.returngoodsdetails.ReturnGoodsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/17
 * 此类或接口用于 退款/售后
 */

public class RefundsOrAfterSaleActivity extends BaseMvpActivity implements OnClickListener, RefundsOrAfterSaleContracts.View {


    @Inject
    RefundsOrAfterSalePresenter presenter;
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
    @BindView(R.id.recyler_view)
    RecyclerView recylerView;


    private List<ReturnGoodsData> datas = new ArrayList<>();
    private boolean hasNext;
    private RefundsAfterSaleRecycleAdapter adapter;
    private Intent intent;


    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();

        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

        tvBaseTitle.setText("退款/售后");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(linearLayoutManager);
        adapter = new RefundsAfterSaleRecycleAdapter(datas, this);
        recylerView.setAdapter(adapter);
        adapter.setOnHeaderClickListener(this);
        presenter.loadRefundsAfterSale(presenter.page, presenter.pageSize);

    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_refunds_or_aftersale;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_refunds_aftersale_detail:
                ReturnGoodsData returnGoodsData = datas.get(position);
                if (returnGoodsData.returnGoodsStatus == 0) {
                    intent = new Intent(this, RefundsDetailActivity.class);
                    intent.putExtra("returnGoodsData", returnGoodsData);
                } else {
                    intent = new Intent(this, ReturnGoodsDetailsActivity.class);
                    intent.putExtra("returnGoodsData", returnGoodsData);
                }
                startActivity(intent);
                break;

        }
    }

    @Override
    public void notify(PageModel<ReturnGoodsData> data) {
        if (data != null && data.dataList.size() > 0) {
            hasNext = data.hasNext;
            presenter.page = data.page + 1;
            datas.addAll(data.dataList);
            adapter.notifyDataSetChanged();
        }
    }
}
