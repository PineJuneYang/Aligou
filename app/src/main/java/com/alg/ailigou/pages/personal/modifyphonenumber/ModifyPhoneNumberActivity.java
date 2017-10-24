package com.alg.ailigou.pages.personal.modifyphonenumber;

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
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.verifynewphonenumber.VerifyNewPhoneNumberActivity;
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

public class ModifyPhoneNumberActivity extends BaseMvpActivity implements ModifyPhoneNumberContracts.View {

    @Inject
    ModifyPhoneNumberPresenter presenter;
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
    @BindView(R.id.tv_modify_phone_number_current_number)
    TextView tvModifyPhoneNumberCurrentNumber;
    @BindView(R.id.tv_modify_phone_number_verification_code)
    TextView tvModifyPhoneNumberVerificationCode;
    @BindView(R.id.et_modify_phone_number_input)
    EditText etModifyPhoneNumberInput;
    @BindView(R.id.tv_modify_phone_number_get_verification_code)
    TextView tvModifyPhoneNumberGetVerificationCode;
    @BindView(R.id.tv_modify_phone_number_next)
    TextView tvModifyPhoneNumberNext;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    private String telNumber;
    private Subscription mSubscribe;
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this
        );
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_modify_phone_number;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvBaseTitle.setText("修改手机号码");
        telNumber = getIntent().getStringExtra("telNumber");
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    @OnClick({R.id.tv_modify_phone_number_get_verification_code, R.id.tv_modify_phone_number_next, R.id.ll_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_phone_number_get_verification_code:
                presenter.getVcode(telNumber, IntConsts.VCODE_UPDATE_OLD_PHONE_NUMBER);
                break;
            case R.id.tv_modify_phone_number_next:
                String sms = etModifyPhoneNumberInput.getText().toString();
                if (TextUtils.isEmpty(sms) || sms.length() < 4 || sms.length() > 6) {
                    ToastUtils.showToast(this, "请输入正确的验证码");
                } else {
                    presenter.verifyID(telNumber, etModifyPhoneNumberInput.getText().toString());
                }
                break;
            case R.id.ll_base_back:
                finish();
                break;

        }
    }
    /**
     * 倒计时
     */
    public void showCountdown() {
        mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                tvModifyPhoneNumberNext.setText("倒计时:"+(60-aLong)+"秒");
                tvModifyPhoneNumberNext.setEnabled(false);
                if (aLong == 60){
                    tvModifyPhoneNumberNext.setText("获取验证码");
                    tvModifyPhoneNumberNext.setEnabled(true);
                    if (mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
                        mSubscribe.unsubscribe();
                    }
                }
            }
        });
    }
    @Override
    public void gotoNext() {
        startActivity(VerifyNewPhoneNumberActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
            mSubscribe.unsubscribe();
        }
    }
}
