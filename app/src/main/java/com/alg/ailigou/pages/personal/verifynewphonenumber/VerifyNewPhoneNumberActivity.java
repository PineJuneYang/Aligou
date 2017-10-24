package com.alg.ailigou.pages.personal.verifynewphonenumber;

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
 * 此类或接口用于
 */

public class VerifyNewPhoneNumberActivity extends BaseMvpActivity implements VerifyNewPhoneNumberContracts.View {


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
    @BindView(R.id.tv_verify_phone_number)
    TextView tvVerifyPhoneNumber;
    @BindView(R.id.et_verify_phone_number_input)
    EditText etVerifyPhoneNumberInput;
    @BindView(R.id.tv_modify_phone_number_verification_code)
    TextView tvModifyPhoneNumberVerificationCode;
    @BindView(R.id.et_verify_phone_number_input_sms)
    EditText etVerifyPhoneNumberInputSms;
    @BindView(R.id.tv_verify_phone_number_get_verification_code)
    TextView tvVerifyPhoneNumberGetVerificationCode;
    @BindView(R.id.tv_verify_phone_number_sure)
    TextView tvVerifyPhoneNumberSure;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    @Inject
    VerifyNewPhoneNumberPresenter presenter;
    private String telNumber;
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
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_verify_phone_number;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvBaseTitle.setText("修改手机号码");
        tvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        telNumber = getIntent().getStringExtra("telNumber");
    }

    @OnClick({R.id.ll_base_back, R.id.tv_verify_phone_number_get_verification_code, R.id.tv_verify_phone_number_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_phone_number_get_verification_code:
                presenter.getVcode(telNumber, IntConsts.VCODE_UPDATE_NEW_PHONE_NUMBER);
                break;
            case R.id.tv_verify_phone_number_sure:
                if (inputOK()) {
                    presenter.updatePhoneNumber(etVerifyPhoneNumberInput.getText().toString(), etVerifyPhoneNumberInputSms.getText().toString());
                }
                break;
            case R.id.ll_base_back:
                finish();
                break;
        }
    }

    private boolean inputOK() {
        String phone = etVerifyPhoneNumberInput.getText().toString();
        String sms = etVerifyPhoneNumberInputSms.getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(sms)) {
            ToastUtils.showToast(this, "手机号或者验证码不能为空");
            return false;
        }
        if (!CheckUtils.isMobliePhoneNumber(phone)) {
            ToastUtils.showToast(this, "请输入正确的手机号");
            return false;
        }
        if (sms.length() < 4 || sms.length() > 6) {
            ToastUtils.showToast(this, "请输入正确的验证码");
            return false;
        }
        return true;
    }

    /**
     * 倒计时
     */
    public void showCountdown() {
        mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                tvVerifyPhoneNumberGetVerificationCode.setText("倒计时:" + (60 - aLong) + "秒");
                tvVerifyPhoneNumberGetVerificationCode.setEnabled(false);
                if (aLong == 60) {
                    tvVerifyPhoneNumberGetVerificationCode.setText("获取验证码");
                    tvVerifyPhoneNumberGetVerificationCode.setEnabled(true);
                    if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
                        mSubscribe.unsubscribe();
                    }
                }
            }
        });
    }

    @Override
    public void showToast(String content) {
        //// TODO: 2017/8/28  
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }
}
