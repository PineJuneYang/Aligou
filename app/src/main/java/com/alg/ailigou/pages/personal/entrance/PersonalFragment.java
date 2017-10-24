package com.alg.ailigou.pages.personal.entrance;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.widget.CommonDialog;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.commonwebview.CommonWebviewActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.feedback.FeedbackActivity;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.ligoudetails.LigouDetailsActivity;
import com.alg.ailigou.pages.personal.login.LoginActivity;
import com.alg.ailigou.pages.personal.mybill.MyBillActivity;
import com.alg.ailigou.pages.personal.mycollection.MyCollectionActivity;
import com.alg.ailigou.pages.personal.myfoot.MyFootNotesActivity;
import com.alg.ailigou.pages.personal.myinfo.MyInfoActivity;
import com.alg.ailigou.pages.personal.myorder.MyOrderActivity;
import com.alg.ailigou.pages.personal.receiveaddress.ReceiveAddressActivity;
import com.alg.ailigou.pages.personal.refundsoraftersale.RefundsOrAfterSaleActivity;
import com.alg.ailigou.pages.personal.setting.SettingActivity;
import com.alg.ailigou.pages.personal.signin.SignInActivity;
import com.alg.ailigou.pages.personal.widget.CheckInDialog;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PersonalFragment extends BaseMvpFragment implements PersonalContrats.View {
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.ll_personal_setting)
    LinearLayout mLlPersonalSetting;
    @BindView(R.id.ll_personal_sport_notice)
    LinearLayout mLlPersonalSportNotice;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.iv_user_icon)
    ImageView mIvUserIcon;
    @BindView(R.id.tv_is_has_login)
    TextView mTvIsHasLogin;
    @BindView(R.id.tv_cheap_ticket_count)
    TextView mTvCheapTicketCount;
    @BindView(R.id.ll_cheap_ticket)
    LinearLayout mLlCheapTicket;
    @BindView(R.id.tv_collect_count)
    TextView mTvCollectCount;
    @BindView(R.id.ll_collect)
    LinearLayout mLlCollect;
    @BindView(R.id.tv_foot_count)
    TextView mTvFootCount;
    @BindView(R.id.ll_foot)
    LinearLayout mLlFoot;
    @BindView(R.id.tv_wait_payment)
    TextView mTvWaitPayment;
    @BindView(R.id.ll_wait_payment)
    LinearLayout mLlWaitPayment;
    @BindView(R.id.tv_wait_ship)
    TextView mTvWaitShip;
    @BindView(R.id.ll_wait_ship)
    LinearLayout mLlWaitShip;
    @BindView(R.id.tv_wait_receipt)
    TextView mTvWaitReceipt;
    @BindView(R.id.ll_wait_receipt)
    LinearLayout mLlWaitReceipt;
    @BindView(R.id.tv_wait_assess)
    TextView mTvWaitAssess;
    @BindView(R.id.ll_wait_assess)
    LinearLayout mLlWaitAssess;
    @BindView(R.id.tv_after_sale)
    TextView mTvAfterSale;
    @BindView(R.id.ll_after_sale)
    LinearLayout mLlAfterSale;
    @BindView(R.id.ll_my_bill)
    LinearLayout mLlMyBill;
    @BindView(R.id.ll_receipt_address)
    LinearLayout mLlReceiptAddress;
    @BindView(R.id.ll_enter_platform)
    LinearLayout mLlEnterPlatform;
    @BindView(R.id.ll_feedbook)
    LinearLayout mLlFeedbook;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_member_level)
    LinearLayout mLlMemberLevel;
    @BindView(R.id.ll_check_in)
    LinearLayout mLlCheckIn;
    @BindView(R.id.ll_my_order)
    LinearLayout mLlMyOrder;
    @BindView(R.id.ll_use_desc)
    LinearLayout mLlUseDesc;
    @Inject
    PersonalPresenter personalPresenter;
    private UserModel mUserModel;

    @Override
    protected BasePresenter getPresenter() {
        return personalPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_personal;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //personalPresenter.loadUserInfo();
        }
    }


    @Override
    public void setViewData(UserModel data) {
        if (data != null) {
            mUserModel = data;
            mTvCheapTicketCount.setText(data.showCountModel.cheapTicket);
            mTvCollectCount.setText(data.showCountModel.collectionCount + "");
            mTvFootCount.setText(data.showCountModel.footprintsCount + "");
            ImageLoadUtils.load(getBaseContext(), data.imageUrl, mIvUserIcon);
            mTvIsHasLogin.setText(data.name);
        }
    }


    @OnClick({R.id.ll_use_desc, R.id.tv_is_has_login, R.id.ll_my_order, R.id.ll_member_level, R.id.ll_check_in, R.id.ll_personal_setting, R.id.ll_personal_sport_notice, R.id.ll_title_right, R.id.iv_user_icon, R.id.ll_cheap_ticket, R.id.ll_collect, R.id.ll_foot, R.id.ll_wait_payment, R.id.ll_wait_ship, R.id.ll_wait_receipt, R.id.ll_wait_assess, R.id.ll_after_sale, R.id.ll_my_bill, R.id.ll_receipt_address, R.id.ll_enter_platform, R.id.ll_feedbook, R.id.ll_customer_service})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_personal_setting:

                break;
            case R.id.ll_personal_sport_notice:
                if (mUserModel != null) {
                    intent = new Intent(getBaseContext(), SettingActivity.class);
                    intent.putExtra("user", mUserModel);
                    startActivity(intent);
                }
                break;
            case R.id.ll_title_right:

                break;
            case R.id.iv_user_icon:
                if (mUserModel != null) {
                    intent = new Intent(getBaseContext(), MyInfoActivity.class);
                    intent.putExtra("user", mUserModel);
                    startActivity(intent);
                }
                break;
            case R.id.ll_cheap_ticket:
                startActivity(LigouDetailsActivity.class);
                break;
            case R.id.ll_collect:
                startActivity(MyCollectionActivity.class);
                break;
            case R.id.ll_foot:
                startActivity(MyFootNotesActivity.class);
                break;
            case R.id.ll_wait_payment://待支付
                intent = new Intent(getBaseContext(), MyOrderActivity.class);
                intent.putExtra("type", PersonalConsts.WAIT_PAR_MONEY);
                startActivity(intent);
                break;
            case R.id.ll_wait_ship://待发货
                intent = new Intent(getBaseContext(), MyOrderActivity.class);
                intent.putExtra("type", PersonalConsts.WAIT_SEND_GOODS);
                startActivity(intent);
                break;
            case R.id.ll_wait_receipt://待收货
                intent = new Intent(getBaseContext(), MyOrderActivity.class);
                intent.putExtra("type", PersonalConsts.WAIT_RECEIVE_GOODS);
                startActivity(intent);
                break;
            case R.id.ll_wait_assess://待评价
                intent = new Intent(getBaseContext(), MyOrderActivity.class);
                intent.putExtra("type", PersonalConsts.WAIT_COMMENT);
                startActivity(intent);
                break;
            case R.id.ll_after_sale://售后
                startActivity(RefundsOrAfterSaleActivity.class);
                break;
            case R.id.ll_use_desc://使用说明
                intent = new Intent(getBaseContext(), CommonWebviewActivity.class);
                intent.putExtra("type", PersonalConsts.USE_EXPLAIN);
                startActivity(intent);
                break;
            case R.id.ll_my_bill://我的账单
                intent = new Intent(getBaseContext(), MyBillActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_receipt_address://收货地址
                startActivity(ReceiveAddressActivity.class);
                break;
            case R.id.ll_enter_platform://入驻平台
                intent = new Intent(getBaseContext(), CommonWebviewActivity.class);
                intent.putExtra("type", PersonalConsts.JOIN_PLATFORM);
                startActivity(intent);
                break;
            case R.id.ll_feedbook://意见反馈
                startActivity(FeedbackActivity.class);
                break;
            case R.id.ll_customer_service://客服热线
                showCallPhoneDialog();
                break;
            case R.id.ll_member_level://会员等级
                intent = new Intent(getBaseContext(), CommonWebviewActivity.class);
                intent.putExtra("type", PersonalConsts.MEMBER_GRADE);
                startActivity(intent);
                break;
            case R.id.tv_is_has_login://登陆注册
                if ("登陆/注册".equals(mTvIsHasLogin.getText())) {
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.ll_check_in://每日签到
                // showCheckInDialog();
                startActivity(SignInActivity.class);
                break;
            case R.id.ll_my_order://查看全部订单
                intent = new Intent(getBaseContext(), MyOrderActivity.class);
                intent.putExtra("type", PersonalConsts.FINISH_ORDER);
                startActivity(intent);
                break;
        }
    }

    /**
     * 签到的dialog
     */
    CheckInDialog checkInDialog;

    private void showCheckInDialog() {
        checkInDialog = new CheckInDialog(getBaseContext());
        checkInDialog.show();
    }


    /**
     * 打电话的dialog
     */
    CommonDialog phoneDialog;

    private void showCallPhoneDialog() {
        phoneDialog = new CommonDialog(getBaseContext(), "123123123");
        phoneDialog.show();
        phoneDialog.getSure().setText("呼叫");
        phoneDialog.getSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions.getInstance(getBaseContext()).request(Manifest.permission.CALL_PHONE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            callPhone("123123123");
                            if (phoneDialog != null) {
                                phoneDialog.dismiss();
                            }
                        }
                    }
                });
            }
        });
        phoneDialog.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneDialog != null) {
                    phoneDialog.dismiss();
                }
            }
        });
    }

    /**
     * 拨号方法
     */
    private void callPhone(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        startActivity(intent);
    }

}
