package com.alg.ailigou.pages.personal.register;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntConsts;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.utils.PhoneUtil;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于 注册页面
 */

public class RegisterActivity extends BaseMvpActivity implements RegisterContracts.View {

    @Inject
    RegisterPresenter presenter;
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
    @BindView(R.id.tv_register_phone_number)
    TextView tvRegisterPhoneNumber;
    @BindView(R.id.et_register_input)
    EditText etRegisterInput;
    @BindView(R.id.tv_register_next)
    TextView tvRegisterNext;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;

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
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvBaseTitle.setText("注册");
        tvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_register;
    }


    @OnClick(R.id.tv_register_next)
    public void onViewClicked() {
        if (hasInput()) {
            //说明有输入
            if (PhoneUtil.isPhone(etRegisterInput.getText().toString().trim())) {
                presenter.getVCode(etRegisterInput.getText().toString().trim(), IntConsts.VCODE_REGISTER);
            } else {
                ToastUtils.showToast(this, "请输入正确的手机号码");
            }
        } else {
            return;
        }
    }


    private boolean hasInput() {
        if (TextUtils.isEmpty(etRegisterInput.getText().toString().trim())) {
            ToastUtils.showToast(this, "手机号不能为空");
            return false;
        } else {
            return true;
        }

    }


    @Override
    public void successRegister() {

    }

    @Override
    public void failedRegister() {

    }

    @Override
    public void successGetCode() {
        Intent intent = new Intent(this, RegisterSettingLoginPwdActivity.class);
        intent.putExtra("PhoneNumber", etRegisterInput.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void failedGetCode() {

    }
}
