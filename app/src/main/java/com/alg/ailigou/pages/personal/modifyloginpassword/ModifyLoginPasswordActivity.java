package com.alg.ailigou.pages.personal.modifyloginpassword;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntConsts;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于 修改登录密码  两个界面弄在一起了
 */

public class ModifyLoginPasswordActivity extends BaseMvpActivity implements ModifyLoginPasswordContracts.View {


    @Inject
    ModifyLoginPasswordPresenter presenter;
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
    @BindView(R.id.tv_modify_login_psw)
    TextView tvModifyLoginPsw;
    @BindView(R.id.et_modify_login_psw_input_old)
    EditText etModifyLoginPswInputOld;
    @BindView(R.id.iv_modify_login_psw_old_eyes)
    ImageView ivModifyLoginPswOldEyes;
    @BindView(R.id.tv_modify_login_psw_new)
    TextView tvModifyLoginPswNew;
    @BindView(R.id.et_modify_login_psw_input_new)
    EditText etModifyLoginPswInputNew;
    @BindView(R.id.iv_modify_login_psw_new_eyes)
    ImageView ivModifyLoginPswNewEyes;
    @BindView(R.id.tv_modify_login_psw_sure_new)
    TextView tvModifyLoginPswSureNew;
    @BindView(R.id.et_modify_login_psw_input_sure_new)
    EditText etModifyLoginPswInputSureNew;
    @BindView(R.id.iv_modify_login_psw_new_sure_eyes)
    ImageView ivModifyLoginPswNewSureEyes;
    @BindView(R.id.tv_modify_login_psw_forget_psw)
    TextView tvModifyLoginPswForgetPsw;
    @BindView(R.id.tv_modify_login_psw_save)
    TextView tvModifyLoginPswSave;
    @BindView(R.id.linear_modify_login_psw_step2)
    LinearLayout linearModifyLoginPswStep2;
    @BindView(R.id.tv_personal_modify_login_psw_title)
    TextView tvPersonalModifyLoginPswTitle;
    @BindView(R.id.tv_modify_login_psw_verification_code)
    TextView tvModifyLoginPswVerificationCode;
    @BindView(R.id.et_modify_phone_number_input)
    EditText etModifyPhoneNumberInput;
    @BindView(R.id.tv_modify_login_psw_get_verification_code)
    TextView tvModifyLoginPswGetVerificationCode;
    @BindView(R.id.tv_modify_login_psw_next)
    TextView tvModifyLoginPswNext;
    @BindView(R.id.tv_to_your_phone)
    TextView tvToYourPhone;
    @BindView(R.id.linear_modify_login_psw_step1)
    LinearLayout linearModifyLoginPswStep1;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    private int type;//type=0  表示是从账户安全中 过来的     type==1  表示 是从当前页面跳转到当前页面  从输验证码跳到修改密码
    private String phoneNumber;
    private Subscription mSubscribe;
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
        type = getIntent().getIntExtra("type", 0);
        phoneNumber = getIntent().getStringExtra("telNumber");
        if (type == 0) {
            tvToYourPhone.setText("我们将发送验证码至你绑定的手机号" + phoneNumber);
            linearModifyLoginPswStep1.setVisibility(View.VISIBLE);
            linearModifyLoginPswStep2.setVisibility(View.GONE);
        } else {
            linearModifyLoginPswStep2.setVisibility(View.VISIBLE);
            linearModifyLoginPswStep1.setVisibility(View.GONE);
        }
        tvBaseTitle.setText("修改登录密码");
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
        return R.layout.alg_act_personal_modify_login_psw;
    }


    @OnClick({R.id.tv_modify_login_psw_next, R.id.tv_modify_login_psw_get_verification_code, R.id.iv_modify_login_psw_old_eyes, R.id.iv_modify_login_psw_new_eyes, R.id.iv_modify_login_psw_new_sure_eyes, R.id.tv_modify_login_psw_forget_psw, R.id.ll_base_back, R.id.tv_modify_login_psw_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_login_psw_next://下一步
                presenter.verifyPhone();
                break;
            case R.id.iv_modify_login_psw_old_eyes:
                break;
            case R.id.iv_modify_login_psw_new_eyes:
                break;
            case R.id.iv_modify_login_psw_new_sure_eyes:
                break;
            case R.id.tv_modify_login_psw_forget_psw:
                break;
            case R.id.tv_modify_login_psw_save:
                //// TODO: 2017/8/28 需要做逻辑判断  非空判断
                if (inputOK()) {
                    presenter.updatePassword(etModifyLoginPswInputOld.getText().toString(), etModifyLoginPswInputNew.getText().toString());
                }
                break;

            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_modify_login_psw_get_verification_code://获取验证码
                presenter.getVCode(phoneNumber, IntConsts.VCODE_UPDATE_LOGIN_PWD);
                break;
        }
    }

    /**
     * 校验
     *
     * @return
     */
    private boolean inputOK() {
        return true;
    }


    @Override
    public void updataSuccess() {

    }

    @Override
    public void showToast(int content) {
        ToastUtils.showToast(this, content);
    }

    @Override
    public void goToNextActivity() {
        Intent intent = new Intent(this, ModifyLoginPasswordActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);
    }

    /**
     * 倒计时
     */
    public void showCountdown() {
        mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                tvModifyLoginPswGetVerificationCode.setText("倒计时:" + (60 - aLong) + "秒");
                tvModifyLoginPswGetVerificationCode.setEnabled(false);
                if (aLong == 60) {
                    tvModifyLoginPswGetVerificationCode.setText("获取验证码");
                    tvModifyLoginPswGetVerificationCode.setEnabled(true);
                    if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
                        mSubscribe.unsubscribe();
                    }
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
            mSubscribe.unsubscribe();
        }
    }


}
