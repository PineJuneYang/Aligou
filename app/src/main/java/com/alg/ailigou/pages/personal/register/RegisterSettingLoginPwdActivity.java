package com.alg.ailigou.pages.personal.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntConsts;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.CheckUtils;
import com.alg.ailigou.library.utils.EdittextUtils;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.login.LoginActivity;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于  注册的设置密码界面
 */

public class RegisterSettingLoginPwdActivity extends BaseMvpActivity implements RegisterContracts.View {


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
    @BindView(R.id.tv_register_setting_pwd_phone_number)
    TextView tvRegisterSettingPwdPhoneNumber;
    @BindView(R.id.tv_register_setting_pwd_verification_code)
    TextView tvRegisterSettingPwdVerificationCode;
    @BindView(R.id.et_register_setting_pwd_input_sms)
    EditText etRegisterSettingPwdInputSms;
    @BindView(R.id.tv_register_setting_pwd_get_verification_code)
    TextView tvRegisterSettingPwdGetVerificationCode;
    @BindView(R.id.tv_register_setting_pwd_new)
    TextView tvRegisterSettingPwdNew;

    @BindView(R.id.iv_register_setting_pwd_eyes)
    ImageView ivRegisterSettingPwdEyes;
    @BindView(R.id.tv_register_setting_pwd_sure_new)
    TextView tvRegisterSettingPwdSureNew;
    @BindView(R.id.iv_register_setting_pwd_sure_eyes)
    ImageView ivRegisterSettingPwdSureEyes;
    @BindView(R.id.tv_register_setting_pwd_alg_protocol)
    TextView tvRegisterSettingPwdAlgProtocol;
    @BindView(R.id.tv_register_setting_pwd_register)
    TextView tvRegisterSettingPwdRegister;
    @BindView(R.id.et_register_setting_pwd_input_new)
    EditText etRegisterSettingPwdInputNew;
    @BindView(R.id.et_register_setting_pwd_sure_new)
    EditText etRegisterSettingPwdSureNew;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    private String phoneNumber;
    private boolean lookPwdNew;
    private boolean lookPwdSure;
    private int countDown = 60;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            countDown--;
            tvRegisterSettingPwdGetVerificationCode.setText(countDown + "秒后重新获取");
            if (countDown > 0) {
                handler.postDelayed(this, 1000);
            } else {
                tvRegisterSettingPwdGetVerificationCode.setText("重新获取");
            }
        }
    };


    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("PhoneNumber");

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvRegisterSettingPwdPhoneNumber.setText("验证短信已发送至" + phoneNumber);
        handler.post(runnable);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvBaseTitle.setText("注册");
        tvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        if (countDown > 0) {
            tvRegisterSettingPwdGetVerificationCode.setClickable(false);
        } else {
            tvRegisterSettingPwdGetVerificationCode.setClickable(true);
        }

    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;

    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_register_setting_pwd;
    }


    @OnClick({R.id.tv_register_setting_pwd_phone_number, R.id.tv_register_setting_pwd_get_verification_code, R.id.iv_register_setting_pwd_eyes, R.id.iv_register_setting_pwd_sure_eyes, R.id.tv_register_setting_pwd_alg_protocol, R.id.tv_register_setting_pwd_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_setting_pwd_phone_number:
                presenter.getVCode(phoneNumber, IntConsts.VCODE_REGISTER);

                break;
            case R.id.tv_register_setting_pwd_get_verification_code:
                break;
            case R.id.iv_register_setting_pwd_eyes:
                lookPwdNew = !lookPwdNew;
                ivRegisterSettingPwdEyes.setSelected(lookPwdNew);
                EdittextUtils.setTransformationMethod(etRegisterSettingPwdInputNew, lookPwdNew);
                break;
            case R.id.iv_register_setting_pwd_sure_eyes:
                lookPwdSure = !lookPwdSure;
                ivRegisterSettingPwdEyes.setSelected(lookPwdSure);
                EdittextUtils.setTransformationMethod(etRegisterSettingPwdInputNew, lookPwdSure);
                break;
            case R.id.tv_register_setting_pwd_alg_protocol:
                break;
            case R.id.tv_register_setting_pwd_register:
                if (hasInputWhenRegister()) {
                    //两次密码一样
                    presenter.register(phoneNumber,
                            etRegisterSettingPwdInputSms.getText().toString().trim(),
                            etRegisterSettingPwdSureNew.getText().toString().trim());
                }
                break;
        }
    }


    private boolean hasInputWhenRegister() {
        String sms = etRegisterSettingPwdInputSms.getText().toString().trim();
        String newPsw = etRegisterSettingPwdInputNew.getText().toString().trim();
        String sureNewPsw = etRegisterSettingPwdSureNew.getText().toString().trim();
        if (TextUtils.isEmpty(sms) || sms.length() < 4 || sms.length() > 6) {
            ToastUtils.showToast(this, "请输入正确的验证码");
            return false;
        }
        if (TextUtils.isEmpty(newPsw) || TextUtils.isEmpty(sureNewPsw)) {
            ToastUtils.showToast(this, "密码不能为空");
            return false;
        }
        if (!newPsw.equals(sureNewPsw)) {
            ToastUtils.showToast(this, "密码不一致");
            return false;
        }
        if (!CheckUtils.isPWD_6_15(newPsw)) {
            ToastUtils.showToast(this, "请输入正确的密码");
        }
        return true;

    }


    @Override
    public void successRegister() {
        //注册成功
        ToastUtils.showToast(this, "注册成功");
        startActivity(LoginActivity.class);
    }

    @Override
    public void failedRegister() {
        ToastUtils.showToast(this, "注册失败");
    }

    @Override
    public void successGetCode() {
        handler.post(runnable);
    }

    @Override
    public void failedGetCode() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
