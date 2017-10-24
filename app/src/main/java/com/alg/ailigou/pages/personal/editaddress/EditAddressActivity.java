package com.alg.ailigou.pages.personal.editaddress;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于 编辑地址的界面
 */

public class EditAddressActivity extends BaseMvpActivity implements EditAddressContracts.View {

    @Inject
    EditAddressPresenter presenter;
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
    @BindView(R.id.et_edit_address_receive_name)
    EditText etEditAddressReceiveName;
    @BindView(R.id.et_edit_address_phone_number)
    EditText etEditAddressPhoneNumber;
    @BindView(R.id.tv_edit_address_area)
    TextView tvEditAddressArea;
    @BindView(R.id.tv_edit_address_province)
    TextView tvEditAddressProvince;
    @BindView(R.id.tv_edit_address_city)
    TextView tvEditAddressCity;
    @BindView(R.id.tv_edit_address_district)
    TextView tvEditAddressDistrict;
    @BindView(R.id.tv_edit_address_default)
    TextView tvEditAddressDefault;
    @BindView(R.id.tv_edit_address_delete)
    TextView tvEditAddressDelete;
    private String name;
    private String editAddressPhone;
    private String editAddressAdress;
    private String editAddressProvince;
    private String editAddressCity;
    private String editAddressDistrict;


    private CityModel provinceModel, countyCity, mCityModel;//省市区
    private OptionsPickerView cityPickerView;
    private boolean isDefault;
    private Drawable defaultIcon;
    private Drawable noDefaultIcon;


    @Override
    protected void initBase() {
        Intent intent = getIntent();
        Bundle editAddress = intent.getBundleExtra("EditAddress");
        name = editAddress.getString("EditAddressName", "王思聪");
        editAddressPhone = editAddress.getString("EditAddressPhone");
        editAddressAdress = editAddress.getString("EditAddressAdress");

        editAddressProvince = editAddress.getString("EditAddressProvince");
        editAddressCity = editAddress.getString("EditAddressCity");
        editAddressDistrict = editAddress.getString("EditAddressDistrict");
        isDefault = editAddress.getBoolean("IsDefault", false);

    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        llBaseEdit.setVisibility(View.VISIBLE);
        tvBaseEdit.setText("完成");
        tvBaseTitle.setText("编辑地址");
        etEditAddressReceiveName.setText(name);
        etEditAddressPhoneNumber.setText(editAddressPhone);

        //初始化省市区
        tvEditAddressProvince.setText(editAddressProvince);
        tvEditAddressCity.setText(editAddressCity);
        tvEditAddressDistrict.setText(editAddressDistrict);

        //初始化是否是默认地址
        defaultIcon = getResources().getDrawable(R.drawable.alg_cart_all_choose);
        noDefaultIcon = getResources().getDrawable(R.drawable.alg_cart_choose);

        if (isDefault){
            defaultIcon.setBounds(0,0,defaultIcon.getIntrinsicWidth(),defaultIcon.getIntrinsicHeight());
            tvEditAddressDefault.setCompoundDrawables(defaultIcon,null,null,null);
        }else{
            noDefaultIcon.setBounds(0,0,noDefaultIcon.getIntrinsicWidth(),noDefaultIcon.getIntrinsicHeight());
            tvEditAddressDefault.setCompoundDrawables(noDefaultIcon,null,null,null);
        }

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
        return R.layout.alg_act_personal_edit_address;
    }


    @OnClick({R.id.ll_base_edit, R.id.tv_edit_address_province, R.id.tv_edit_address_city, R.id.tv_edit_address_district, R.id.ll_base_back,R.id.tv_edit_address_default, R.id.tv_edit_address_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_edit:
                //点击完成

                break;
            case R.id.tv_edit_address_province: //省
                presenter.loadCityList(0,UniconConsts.TYPE_PROVINCE);
                break;
            case R.id.tv_edit_address_city:  //市
                if (provinceModel == null) {
                    ToastUtils.showToast(this, "请先选择省");
                } else {
                    presenter.loadCityList(provinceModel.areaId, UniconConsts.TYPE_CITY);
                }
                break;
            case R.id.tv_edit_address_district: //区
                if (mCityModel == null) {
                    ToastUtils.showToast(this, "请先选择市");
                } else {
                    presenter.loadCityList(mCityModel.areaId, UniconConsts.TYPE_COUNTY);
                }
                break;
            case R.id.tv_edit_address_default:
                if (isDefault){//如果开始时默认地址
                    noDefaultIcon.setBounds(0,0,noDefaultIcon.getIntrinsicWidth(),noDefaultIcon.getIntrinsicHeight());
                    tvEditAddressDefault.setCompoundDrawables(noDefaultIcon,null,null,null);
                }else {
                    defaultIcon.setBounds(0,0,defaultIcon.getIntrinsicWidth(),defaultIcon.getIntrinsicHeight());
                    tvEditAddressDefault.setCompoundDrawables(defaultIcon,null,null,null);
                }
                isDefault = !isDefault;
                break;
            case R.id.tv_edit_address_delete:
                //点击删除
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
                            tvEditAddressProvince.setText(provinceModel.areaName);
                            tvEditAddressCity.setText(editAddressCity);
                            tvEditAddressDistrict.setText(editAddressDistrict);
                        }
                        break;
                    case UniconConsts.TYPE_CITY:
                        if (cityModels == null || !cityModels.equals(cityModels.get(options1))) {
                            mCityModel = cityModels.get(options1);
                            countyCity = null;
//                            tvEditAddressProvince.setText(provinceModel.areaName);
                            tvEditAddressCity.setText(mCityModel.areaName);
                            tvEditAddressDistrict.setText(editAddressDistrict);
                        }
                        break;
                    case UniconConsts.TYPE_COUNTY:
                        countyCity = cityModels.get(options1);
                        tvEditAddressDistrict.setText(countyCity.areaName);
                        break;
                }

                cityPickerView.dismiss();
            }
        });

        cityPickerView.show();


    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }
}
