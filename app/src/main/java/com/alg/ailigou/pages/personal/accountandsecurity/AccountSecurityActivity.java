package com.alg.ailigou.pages.personal.accountandsecurity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.modifyloginpassword.ModifyLoginPasswordActivity;
import com.alg.ailigou.pages.personal.modifyphonenumber.ModifyPhoneNumberActivity;
import com.alg.ailigou.pages.personal.resetloginpassword.ResetLoginPasswordActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于 账户安全的首页
 */

public class AccountSecurityActivity extends BaseMvpActivity implements AccountSecurityContracts.View {

    @Inject
    AccountSecurityPresenter presenter;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_account_security_phonenumber_next)
    ImageView ivAccountSecurityPhonenumberNext;
    @BindView(R.id.relative_change_phone_number)
    RelativeLayout relativeChangePhoneNumber;
    @BindView(R.id.iv_account_security_login_next)
    ImageView ivAccountSecurityLoginNext;
    @BindView(R.id.relative_change_login_psw)
    RelativeLayout relativeChangeLoginPsw;
    @BindView(R.id.iv_account_security_pay_next)
    ImageView ivAccountSecurityPayNext;
    @BindView(R.id.relative_change_pay_psw)
    RelativeLayout relativeChangePayPsw;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    @BindView(R.id.tv_telnumber)
    TextView tvTelNumber;
    private String telNumber;

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
        tvBaseTitle.setText("账户与安全");
        tvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        telNumber = getIntent().getStringExtra("telNumber");
        if (!TextUtils.isEmpty(telNumber)){
            tvTelNumber.setText(telNumber.substring(0,3)+"****"+telNumber.substring(telNumber.length()-4,telNumber.length()));
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_account_security;
    }


    @OnClick({R.id.relative_change_phone_number, R.id.ll_base_back, R.id.relative_change_login_psw, R.id.relative_change_pay_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relative_change_phone_number:
                startActivityWithExtra(ModifyPhoneNumberActivity.class, "telNumber", telNumber);
                break;
            case R.id.relative_change_login_psw:
                Intent intent = new Intent(this, ModifyLoginPasswordActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("telNumber", telNumber);
                startActivity(intent);
                break;
            case R.id.relative_change_pay_psw:
                startActivityWithExtra(ResetLoginPasswordActivity.class, "type", 1);
                break;

            case R.id.ll_base_back:
                finish();
                break;
        }
    }
}
