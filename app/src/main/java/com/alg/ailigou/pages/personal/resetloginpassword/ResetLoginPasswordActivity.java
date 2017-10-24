package com.alg.ailigou.pages.personal.resetloginpassword;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
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
import com.alg.ailigou.library.utils.CheckUtils;
import com.alg.ailigou.library.utils.EdittextUtils;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.login.LoginActivity;
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
 * 此类或接口用于  此界面可以作为  "修改支付密码界面",界面UI一样,下面已type标记
 */

public class ResetLoginPasswordActivity extends BaseMvpActivity implements ResetLoginPasswordConstracts.View {

    @Inject
    ResetLoginPasswordPresenter presenter;
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
    @BindView(R.id.tv_reset_login_psw_phone_number)
    TextView tvResetLoginPswPhoneNumber;
    @BindView(R.id.et_reset_login_psw_input)
    EditText etResetLoginPswInput;
    @BindView(R.id.tv_reset_login_psw_verification_code)
    TextView tvResetLoginPswVerificationCode;
    @BindView(R.id.et_reset_login_psw_input_sms)
    EditText etResetLoginPswInputSms;
    @BindView(R.id.tv_reset_login_psw_get_verification_code)
    TextView tvResetLoginPswGetVerificationCode;
    @BindView(R.id.tv_reset_login_psw_new)
    TextView tvResetLoginPswNew;
    @BindView(R.id.et_reset_login_psw_input_new)
    EditText etResetLoginPswInputNew;
    @BindView(R.id.iv_reset_login_psw_new_eyes)
    ImageView ivResetLoginPswNewEyes;
    @BindView(R.id.tv_reset_login_psw_sure_new)
    TextView tvResetLoginPswSureNew;
    @BindView(R.id.et_reset_login_psw_input_sure_new)
    EditText etResetLoginPswInputSureNew;
    @BindView(R.id.iv_modify_login_psw_new_sure_eyes)
    ImageView ivModifyLoginPswNewSureEyes;
    @BindView(R.id.tv_reset_login_psw_save)
    TextView tvResetLoginPswSave;
    private int type;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    private boolean isCanSeePWd1;//上面的大眼睛
    private boolean isCanSeePWd2;//确认新密码的眼睛
    private Subscription mSubscribe;

    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", -1);
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        if (type == 0) {
            tvBaseTitle.setText("重置登录密码");
        } else {
            tvBaseTitle.setText("修改支付密码");
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_reset_login_psw;
    }


    @OnClick({R.id.tv_reset_login_psw_get_verification_code, R.id.iv_reset_login_psw_new_eyes, R.id.iv_modify_login_psw_new_sure_eyes, R.id.tv_reset_login_psw_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset_login_psw_get_verification_code:
                if (hasInputWhenGetVCode()) {
                    presenter.getVCode(etResetLoginPswInput.getText().toString().trim(),type==0? IntConsts.VCODE_RESET_PWD:IntConsts.VCODE_UPDATE_PAY_PWD);
                } else {
                    return;
                }
                break;
            case R.id.iv_reset_login_psw_new_eyes:
                isCanSeePWd1 = !isCanSeePWd1;
                ivResetLoginPswNewEyes.setSelected(isCanSeePWd1);
                EdittextUtils.setTransformationMethod(etResetLoginPswInputNew, isCanSeePWd1);
                break;
            case R.id.iv_modify_login_psw_new_sure_eyes:
                isCanSeePWd2 = !isCanSeePWd2;
                ivModifyLoginPswNewSureEyes.setSelected(isCanSeePWd2);
                EdittextUtils.setTransformationMethod(etResetLoginPswInputSureNew, isCanSeePWd2);
                break;
            case R.id.tv_reset_login_psw_save:
                if (hasInputWhenSave()) {
                    presenter.resetLoginPwd(etResetLoginPswInput.getText().toString().trim(), etResetLoginPswInputSms.getText().toString().trim(), etResetLoginPswInputNew.getText().toString().trim(), type);
                }
                break;
        }
    }

    @Override
    public void successfulGetVCode() {
        ToastUtils.showToast(this, "验证码发送成功");
        showCountdown();
    }

    /**
     * 倒计时
     */
   private void showCountdown() {
       mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
           @Override
           public void call(Long aLong) {
               tvResetLoginPswGetVerificationCode.setText("倒计时:"+(60-aLong)+"秒");
               tvResetLoginPswGetVerificationCode.setEnabled(false);
               if (aLong == 60){
                   tvResetLoginPswGetVerificationCode.setText("获取验证码");
                   tvResetLoginPswGetVerificationCode.setEnabled(true);
                  if (mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
                      mSubscribe.unsubscribe();
                  }
               }
           }
       });
   }

    @Override
    public void successfulResetPwd() {
        ToastUtils.showToast(this, "重置密码成功");
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }

    @Override
    public void failedResetPwd() {
        ToastUtils.showToast(this, "重置密码失败");
    }

    @Override
    public void FailedGetVCode() {
        ToastUtils.showToast(this, "获取验证码失败");
    }


    private boolean hasInputWhenGetVCode() {
        if (TextUtils.isEmpty(etResetLoginPswInput.getText().toString().trim())) {
            ToastUtils.showToast(this, "用户名或验证码或密码不能为空");
            return false;
        } else {
            return true;
        }

    }


    private boolean hasInputWhenSave() {
        String phone = etResetLoginPswInput.getText().toString().trim();
        String sms = etResetLoginPswInputSms.getText().toString().trim();
        String newPsw = etResetLoginPswInputNew.getText().toString().trim();
        String sureNewPsw = etResetLoginPswInputSureNew.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || !CheckUtils.isMobliePhoneNumber(phone)) {
            ToastUtils.showToast(this, "请输入正确的手机号");
            return false;
        }
        if (TextUtils.isEmpty(sms) || sms.length() < 4 || sms.length() > 6) {
            ToastUtils.showToast(this, "请输入正确的验证码");
            return false;
        }
        if (TextUtils.isEmpty(newPsw) || TextUtils.isEmpty(sureNewPsw)) {
            ToastUtils.showToast(this, "密码不能为空");
            return false;
        }
        if (!newPsw.equals(sureNewPsw)){
            ToastUtils.showToast(this, "密码不一致");
            return false;
        }
        if (!CheckUtils.isPWD_6_15(newPsw)){
            ToastUtils.showToast(this, "请输入正确的密码");
        }
       return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
            mSubscribe.unsubscribe();
        }
    }
}
