package com.alg.ailigou.pages.personal.login;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.EdittextUtils;
import com.alg.ailigou.pages.main.entrance.MainActivity;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.register.RegisterActivity;
import com.alg.ailigou.pages.personal.resetloginpassword.ResetLoginPasswordActivity;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/5.
 * 此类或接口用于 登录的activity
 */

public class LoginActivity extends BaseMvpActivity implements LoginContracts.View {

    @Inject
    LoginPresenter presenter;

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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_see_pwd)
    ImageView ivSeePwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_wecaht)
    LinearLayout llWecaht;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_weibo)
    LinearLayout llWeibo;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    boolean isCanSeePWd;

    @Override
    protected void initBase() {

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        initView();

    }

    private void initView() {
        tvBaseTitle.setText("登录");
        llBaseEdit.setVisibility(View.VISIBLE);
        tvBaseEdit.setText("注册");
        tvBaseTitle.setTextColor(Color.BLACK);
        tvBaseEdit.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_login;
    }

    @Override
    public void successfulLogin(String token) {
        //登录成功,跳转到首页
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void failedLogin() {
        //失败的回调
        ToastUtils.showToast(this, "账号或密码错误,登录失败");
    }


    @OnClick({R.id.ll_base_edit, R.id.iv_see_pwd, R.id.tv_forget_pwd, R.id.tv_login, R.id.ll_wecaht, R.id.ll_qq, R.id.ll_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_see_pwd:
                isCanSeePWd = !isCanSeePWd;
                ivSeePwd.setSelected(isCanSeePWd);
                EdittextUtils.setTransformationMethod(etPwd, isCanSeePWd);
                break;
            case R.id.tv_forget_pwd:
                //进入到重置密码
                Intent intent = new Intent(this, ResetLoginPasswordActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.tv_login:
                if (hasInput()) {
                    //说明用户名和密码不为空
                    presenter.login(etName.getText().toString(), etPwd.getText().toString());
                } else {
                    return;
                }
                break;
            case R.id.ll_wecaht:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.ll_qq:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.ll_weibo:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
                break;
            case R.id.ll_base_edit:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    private boolean hasInput() {
        if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPwd.getText().toString())) {
            ToastUtils.showToast(this, "用户名或密码不能为空");
            return false;
        }
        return true;


    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "开始", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();


        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
