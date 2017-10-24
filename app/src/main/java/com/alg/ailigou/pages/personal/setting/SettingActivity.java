package com.alg.ailigou.pages.personal.setting;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.BankModel;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.widget.IosDialog;
import com.alg.ailigou.common.widget.UMShareDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.CacheManager;
import com.alg.ailigou.mock.cart.CartList;
import com.alg.ailigou.pages.personal.aboutaligo.AboutAligoActivity;
import com.alg.ailigou.pages.personal.accountandsecurity.AccountSecurityActivity;
import com.alg.ailigou.pages.personal.addbankcard.AddBankCardActivity;
import com.alg.ailigou.pages.personal.addbankcardinfo.AddBankCardInfoActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于
 */

public class SettingActivity extends BaseMvpActivity {

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
    @BindView(R.id.rl_account_safe)
    RelativeLayout mRlAccountSafe;
    @BindView(R.id.rl_my_bank_card)
    RelativeLayout mRlMyBankCard;
    @BindView(R.id.rl_share_app)
    RelativeLayout mRlShareApp;
    @BindView(R.id.rl_app_update)
    RelativeLayout mRlAppUpdate;
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout mRlClearCache;
    @BindView(R.id.rl_abort_alg)
    RelativeLayout mRlAbortAlg;
    @BindView(R.id.tv_login_out)
    TextView mTvLoginOut;
    @BindView(R.id.tv_cache)
    TextView mTvCache;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    private UserModel user;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_setting;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        mTvCache.setText(CacheManager.getTotalCacheSize(this));
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        Intent intent = getIntent();
        user = (UserModel)intent.getSerializableExtra("user");
        mTvBaseTitle.setText("设置");
        mTvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    @OnClick({R.id.ll_base_back, R.id.rl_account_safe, R.id.rl_my_bank_card, R.id.rl_share_app, R.id.rl_app_update, R.id.rl_clear_cache, R.id.rl_abort_alg, R.id.tv_login_out})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.rl_account_safe://账号与安全
                if (user!=null){
                    intent = new Intent(this, AccountSecurityActivity.class);
                    intent.putExtra("telNumber", user.telNumber);
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_bank_card://我的银行卡
                BankModel bank = user.bank;
                if (bank == null|| TextUtils.isEmpty(bank.bankNumber)) {
                    startActivity(AddBankCardActivity.class);
                } else {
                    intent = new Intent(this, AddBankCardInfoActivity.class);
                    intent.putExtra("bankData",bank);
                    intent.putExtra("bankStatusType", PersonalConsts.HAS_BANK_NUMBER);
                    startActivity(intent);
                }
                break;
            case R.id.rl_share_app://分享app
                UMShareDialog umDialog = new UMShareDialog(this, CartList.getShareModel());
                umDialog.show();
                break;
            case R.id.rl_app_update://app升级
                break;
            case R.id.rl_clear_cache://清理缓存
                showClearCacheDialog();

                break;
            case R.id.rl_abort_alg://关于爱利购
                startActivity(AboutAligoActivity.class);
                break;
            case R.id.tv_login_out://退出当前账号
                setLoginOut();
                break;
        }
    }

    private void showClearCacheDialog() {
        final IosDialog dialog = new IosDialog(this);
        dialog.setContent("确实清除缓存么?");
        dialog.setSureText("是");
        dialog.setCancelText("取消");
        dialog.show();
        dialog.setIosDialogCallBack(new IosDialog.IosDialogCallBack() {
            @Override
            public void onSure() {
                CacheManager.clearAllCache(SettingActivity.this);
                mTvCache.setText(CacheManager.getTotalCacheSize(SettingActivity.this));
            }

            @Override
            public void onCancle() {
                dialog.dismiss();
            }
        });
    }

    private void setLoginOut() {
        UserApi.signOut(new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
                    // TODO: 2017/8/28  
                }
            }
        });
    }
}
