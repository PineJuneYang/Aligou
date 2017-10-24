package com.alg.ailigou.pages.personal.addaddress;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.union.consts.UniconConsts;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于 添加地址的界面
 */

public class AddAddressActivity extends BaseMvpActivity implements AddAddressContracts.View {

    @Inject
    AddAddressPresenter presenter;

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
    @BindView(R.id.et_add_address_receive_name)
    EditText etAddAddressReceiveName;
    @BindView(R.id.et_add_address_phone_number)
    EditText etAddAddressPhoneNumber;
    @BindView(R.id.tv_add_address_area)
    TextView tvAddAddressArea;
    @BindView(R.id.tv_add_address_province)
    TextView tvAddAddressProvince;
    @BindView(R.id.tv_add_address_city)
    TextView tvAddAddressCity;
    @BindView(R.id.tv_add_address_district)
    TextView tvAddAddressDistrict;
    @BindView(R.id.tv_add_address_detail_address)
    TextView tvAddAddressDetailAddress;
    @BindView(R.id.et_add_address_detail_address)
    EditText etAddAddressDetailAddress;
    @BindView(R.id.tv_add_address_default)
    TextView tvAddAddressDefault;
    @BindView(R.id.rl_base_title)
    RelativeLayout rLBaseTitle;
    private boolean isDefault;
    private Drawable defaultIcon;
    private Drawable noDefaultIcon;

    private CityModel provinceModel, countyCity, mCityModel;//省市区
    private OptionsPickerView cityPickerView;


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
        tvBaseTitle.setText("新增地址");
        llBaseEdit.setVisibility(View.VISIBLE);
        tvBaseEdit.setText("保存");
        rLBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);

        defaultIcon = getResources().getDrawable(R.drawable.alg_cart_all_choose);
        noDefaultIcon = getResources().getDrawable(R.drawable.alg_cart_choose);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_add_address;
    }



    @OnClick({R.id.ll_base_edit,R.id.tv_add_address_province,R.id.ll_base_back,R.id.tv_add_address_city,R.id.tv_add_address_district, R.id.tv_add_address_default})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_address_province:

                presenter.loadCityList(0, UniconConsts.TYPE_PROVINCE);
                break;

            case R.id.tv_add_address_city:
                if (provinceModel == null) {
                    ToastUtils.showToast(this, "请先选择省");
                } else {
                    presenter.loadCityList(provinceModel.areaId, UniconConsts.TYPE_CITY);
                }

                break;

            case R.id.tv_add_address_district:
                if (mCityModel == null) {
                    ToastUtils.showToast(this, "请先选择市");
                } else {
                    presenter.loadCityList(mCityModel.areaId, UniconConsts.TYPE_COUNTY);
                }
                break;

            case R.id.ll_base_edit:

//
//                if (TextUtils.isEmpty(etAddAddressReceiveName.getText().toString().trim())){
//                    ToastUtils.showToast(this,"亲,收货人名不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(etAddAddressPhoneNumber.getText().toString().trim())){
//                    ToastUtils.showToast(this,"亲,电话号码不能为空");
//                    return;
//                }
//
//                if (provinceModel ==null||mCityModel==null||countyCity==null){
//                    ToastUtils.showToast(this,"亲,地址不能为空");
//                    return;
//                }
//
//
//                if (TextUtils.isEmpty(etAddAddressDetailAddress.getText().toString().trim())){
//                    ToastUtils.showToast(this,"亲,收货地址不能为空");
//                    return;
//                }


                presenter.addAddress(0,etAddAddressReceiveName.getText().toString(),
                        etAddAddressPhoneNumber.getText().toString(),
                        provinceModel,
                        mCityModel,
                        countyCity,
                        etAddAddressDetailAddress.getText().toString(),
                        isDefault
                );

                break;
            case R.id.tv_add_address_default:

                if (isDefault){//如果开始时默认地址
                    noDefaultIcon.setBounds(0,0,noDefaultIcon.getIntrinsicWidth(),noDefaultIcon.getIntrinsicHeight());
                    tvAddAddressDefault.setCompoundDrawables(noDefaultIcon,null,null,null);
                }else {
                    defaultIcon.setBounds(0,0,defaultIcon.getIntrinsicWidth(),defaultIcon.getIntrinsicHeight());
                    tvAddAddressDefault.setCompoundDrawables(defaultIcon,null,null,null);
                }
                isDefault = !isDefault;

                break;



            case R.id.ll_base_back:
                finish();
                break;
        }
    }

    @Override
    public void setCityData(final int type, final List<CityModel> cityModels) {
        cityPickerView = PickerUtils.getCommonPickerView(this, "城市选择", cityModels, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                switch (type) {
                    case UniconConsts.TYPE_PROVINCE:
                        if (provinceModel == null || (!provinceModel.equals(cityModels.get(options1)))) {
                            provinceModel = cityModels.get(options1);
                            mCityModel = null;
                            countyCity = null;
                            tvAddAddressProvince.setText(provinceModel.areaName);
                            tvAddAddressCity.setText("");
                            tvAddAddressDistrict.setText("");
                        }
                        break;
                    case UniconConsts.TYPE_CITY:
                        if (cityModels == null || !cityModels.equals(cityModels.get(options1))) {
                            mCityModel = cityModels.get(options1);
                            countyCity = null;
//                            tvEditAddressProvince.setText(provinceModel.areaName);
                            tvAddAddressCity.setText(mCityModel.areaName);
                            tvAddAddressDistrict.setText("");
                        }
                        break;
                    case UniconConsts.TYPE_COUNTY:
                        countyCity = cityModels.get(options1);
                        tvAddAddressDistrict.setText(countyCity.areaName);
                        break;
                }

                cityPickerView.dismiss();
            }
        });

        cityPickerView.show();
    }

    @Override
    public void updateSuccessful() {
        ToastUtils.showToast(this,"添加成功");
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }
}
