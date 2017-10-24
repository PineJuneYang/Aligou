package com.alg.ailigou.pages.union.unionsearch;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.UnionTypeModel;
import com.alg.ailigou.common.requestmodel.SearchUnionRequest;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.union.consts.UniconConsts;
import com.alg.ailigou.pages.union.entrance.UnionActivity;
import com.alg.ailigou.pages.union.inject.DaggerUnionComponent;
import com.alg.ailigou.pages.union.inject.UnionModule;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于 联盟商家搜索
 */

public class UnionSearchActivity extends BaseMvpActivity implements UnionSearchContracts.View {
    @Inject
    UnionSearchPresenter mPresenter;
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
    @BindView(R.id.et_union_name)
    EditText mEtUnionName;
    @BindView(R.id.tv_industry)
    TextView mTvIndustry;
    @BindView(R.id.tv_select_province)
    TextView mTvSelectProvince;
    @BindView(R.id.tv_select_city)
    TextView mTvSelectCity;
    @BindView(R.id.tv_select_county)
    TextView mTvSelectCounty;
    @BindView(R.id.btn_sure)
    TextView mTvSure;

    private String storeName;//手动填写的商店名称
    private UnionTypeModel unionTypeModel;
    private CityModel provinceModel, countyCity, mCityModel;//省市区
    private OptionsPickerView cityPickerView;
    private OptionsPickerView typePickerView;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_union_search;
    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerUnionComponent.builder().unionModule(new UnionModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("联盟商家搜索");
        mEtUnionName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storeName = mEtUnionName.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ll_base_back, R.id.tv_industry, R.id.tv_select_province, R.id.tv_select_city, R.id.btn_sure, R.id.tv_select_county})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_industry://分类
                mPresenter.loadUnionTypeList();
                break;
            case R.id.tv_select_province://省,
                mPresenter.loadCityList(1, UniconConsts.TYPE_PROVINCE);
                break;
            case R.id.tv_select_city://市
                if (provinceModel == null) {
                    ToastUtils.showToast(this, "请先选择省");
                } else {
                    mPresenter.loadCityList(provinceModel.areaId, UniconConsts.TYPE_CITY);
                }

                break;
            case R.id.tv_select_county://区
                if (mCityModel == null) {
                    ToastUtils.showToast(this, "请先选择市");
                } else {
                    mPresenter.loadCityList(mCityModel.areaId, UniconConsts.TYPE_COUNTY);
                }
                break;
            case R.id.btn_sure://确定
                if (storeName == null & provinceModel == null & countyCity == null & mCityModel == null & unionTypeModel == null) {
                    ToastUtils.showToast(this, "请至少选择一个内容进行搜索");
                } else {
                    gotoSearch();

                }
                break;
        }
    }

    private void gotoSearch() {
        SearchUnionRequest request = new SearchUnionRequest();
        request.unionName = storeName;
        request.typeId = unionTypeModel == null ? 0 : unionTypeModel.id;
        Intent intent = new Intent(this, UnionActivity.class);
        if (countyCity != null) {
            request.areaId = countyCity.areaId;
        } else if (mCityModel != null) {
            request.areaId = mCityModel.areaId;
        } else if (provinceModel != null) {
            request.areaId = provinceModel.areaId;
        }
        intent.putExtra("request",request);
        startActivity(intent);
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
                            mTvSelectProvince.setText(provinceModel.areaName);
                            mTvSelectCity.setText("");
                            mTvSelectCounty.setText("");
                        }
                        break;
                    case UniconConsts.TYPE_CITY:
                        if (cityModels == null || !cityModels.equals(cityModels.get(options1))) {
                            mCityModel = cityModels.get(options1);
                            countyCity = null;
                            mTvSelectProvince.setText(provinceModel.areaName);
                            mTvSelectCity.setText(mCityModel.areaName);
                            mTvSelectCounty.setText("");
                        }
                        break;
                    case UniconConsts.TYPE_COUNTY:
                        countyCity = cityModels.get(options1);
                        mTvSelectCounty.setText(countyCity.areaName);
                        break;
                }

                cityPickerView.dismiss();
            }
        });
        cityPickerView.show();
    }

    @Override
    public void setUnionTypeData(final List<UnionTypeModel> unionTypeData) {
        typePickerView = PickerUtils.getCommonPickerView(this, "请选择行业", unionTypeData, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                unionTypeModel = unionTypeData.get(options1);
                mTvIndustry.setText(unionTypeModel.typeName);
                typePickerView.dismiss();
            }
        });
        typePickerView.show();
    }
}
