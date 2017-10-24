package com.alg.ailigou.pages.personal.receiveaddress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.personal.addaddress.AddAddressActivity;
import com.alg.ailigou.pages.personal.editaddress.EditAddressActivity;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.receiveaddress.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于 收货地址的activity
 */

public class ReceiveAddressActivity extends BaseMvpActivity implements ReceiveAddressContracts.View, OnClickListener {

    @Inject
    ReceiveAddressPresenter presenter;
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
    @BindView(R.id.ll_receive_address_empty)
    LinearLayout llReceiveAddressEmpty;
    @BindView(R.id.rv_receive_address_address)
    RecyclerView mRecycleView;
    @BindView(R.id.tv_receive_address_new_add)
    TextView tvReceiveAddressNewAdd;
    @BindView(R.id.rl_base_title)
    RelativeLayout rLBaseTitle;
    private List<ShippingAddressModel> shippingAddressModels = new ArrayList<>();
    private AddressAdapter adapter;


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
    protected int layoutId() {
        return R.layout.alg_act_personal_receive_address;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvBaseTitle.setText("收货地址");
        llReceiveAddressEmpty.setVisibility(View.GONE);
        rLBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        adapter = new AddressAdapter(shippingAddressModels, this);
        mRecycleView.setAdapter(adapter);
        presenter.loadReceiveAddress();

    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

        adapter.setOnHeaderClickListener(this);

    }


    @Override
    public void notifyAddress(List<ShippingAddressModel> data) {
        if (data == null || data.size() == 0) {
            //没有数据
            mRecycleView.setVisibility(View.GONE);
            llReceiveAddressEmpty.setVisibility(View.VISIBLE);
        } else {
            //

            shippingAddressModels.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {

            case R.id.tv_item_receive_address_delete:
                presenter.deleteReceiveAddress(shippingAddressModels.get(position).id);
                break;

            case R.id.tv_item_receive_address_edit:
                Intent intent = new Intent(this, EditAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("EditAddressName", shippingAddressModels.get(position).name);
                bundle.putString("EditAddressPhone", shippingAddressModels.get(position).telNumber);
                bundle.putString("EditAddressAdress", shippingAddressModels.get(position).address);

                bundle.putString("EditAddressProvince", shippingAddressModels.get(position).province.areaName);
                bundle.putString("EditAddressCity", shippingAddressModels.get(position).city.areaName);
                bundle.putString("EditAddressDistrict", shippingAddressModels.get(position).district.areaName);
                bundle.putBoolean("IsDefault", shippingAddressModels.get(position).isDefault);

                intent.putExtra("EditAddress", bundle);
                startActivity(intent);


                break;


        }


    }


    @OnClick({R.id.ll_base_back, R.id.tv_receive_address_new_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_receive_address_new_add:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }
}
