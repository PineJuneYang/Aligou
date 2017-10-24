package com.alg.ailigou.pages.personal.inject;

import com.alg.ailigou.pages.personal.accountandsecurity.AccountSecurityActivity;
import com.alg.ailigou.pages.personal.addaddress.AddAddressActivity;
import com.alg.ailigou.pages.personal.addbankcardinfo.AddBankCardInfoActivity;
import com.alg.ailigou.pages.personal.applyreturnmoney.ApplyReturnMoneyActivity;
import com.alg.ailigou.pages.personal.commentdetail.CommentDetailActivity;
import com.alg.ailigou.pages.personal.commonorderdetails.CommonOrderDetailsActivity;
import com.alg.ailigou.pages.personal.editaddress.EditAddressActivity;
import com.alg.ailigou.pages.personal.entrance.PersonalFragment;
import com.alg.ailigou.pages.personal.login.LoginActivity;
import com.alg.ailigou.pages.personal.modifyloginpassword.ModifyLoginPasswordActivity;
import com.alg.ailigou.pages.personal.modifyphonenumber.ModifyPhoneNumberActivity;
import com.alg.ailigou.pages.personal.moneychange.MoneyChangeActivity;
import com.alg.ailigou.pages.personal.mycollection.MyCollectionActivity;
import com.alg.ailigou.pages.personal.mycollection.fragment.MyCollectionFragment;
import com.alg.ailigou.pages.personal.myfoot.MyFootNotesActivity;
import com.alg.ailigou.pages.personal.myinfo.MyInfoActivity;
import com.alg.ailigou.pages.personal.myorder.MyOrderActivity;
import com.alg.ailigou.pages.personal.myorder.fragment.MyOrderFragment;
import com.alg.ailigou.pages.personal.offlineorder.OffLineOrderActivity;
import com.alg.ailigou.pages.personal.onlineorder.OnLineOrderActivity;
import com.alg.ailigou.pages.personal.receiveaddress.ReceiveAddressActivity;
import com.alg.ailigou.pages.personal.refundsoraftersale.RefundsOrAfterSaleActivity;
import com.alg.ailigou.pages.personal.register.RegisterActivity;
import com.alg.ailigou.pages.personal.register.RegisterSettingLoginPwdActivity;
import com.alg.ailigou.pages.personal.resetloginpassword.ResetLoginPasswordActivity;
import com.alg.ailigou.pages.personal.seelogistics.SeeLogisticsActivity;
import com.alg.ailigou.pages.personal.verifynewphonenumber.VerifyNewPhoneNumberActivity;
import com.alg.ailigou.pages.personal.withdrawNotes.WthdrawNotesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigou.pages.personal.inject
 * Created by Chris Chen on 2017/7/7 15:29.
 * Explain:个人中心dagger2注入器
 */
@Singleton
@Component(modules = {PersonalModule.class})
public interface PersonalComponent {
    void inject(PersonalFragment personalFragment);

    void inject(AddBankCardInfoActivity addBankCardInfoActivity);//添加银行卡和银行卡详情

    void inject(VerifyNewPhoneNumberActivity verifyNewPhoneNumberActivity);

    void inject(ResetLoginPasswordActivity resetLoginPasswordActivity);

    void inject(ModifyLoginPasswordActivity modifyLoginPasswordActivity);

    void inject(AccountSecurityActivity accountSecurityActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(RegisterSettingLoginPwdActivity registerSettingLoginPwdActivity);

    void inject(WthdrawNotesActivity wthdrawNotesActivity);//提现记录

    void inject(MyCollectionActivity myCollectionActivity); //我的收藏

    void inject(MyCollectionFragment myCollectionFragment); //我的收藏里面的fragment

    void inject(MyFootNotesActivity myFootNotesActivity);//我的足迹

    void inject(MyOrderActivity myOrderActivity);  //我的订单

    void inject(SeeLogisticsActivity seeLogisticsActivity);//查看物流信息

    void inject(MyOrderFragment myOrderFragment); //我的订单的fragment

    void inject(CommentDetailActivity commentDetailActivity);//评价详情

    void inject(ReceiveAddressActivity receiveAddressActivity); //收货地址

    void inject(EditAddressActivity editAddressActivity);  //编辑地址

    void inject(OnLineOrderActivity onLineOrderActivity); //线上订单

    void inject(AddAddressActivity addAddressActivity);  //新增地址

    void inject(OffLineOrderActivity offLineOrderActivity); //线下订单

    void inject(RefundsOrAfterSaleActivity refundsOrAfterSaleActivity); //退款/售后

    void inject(ApplyReturnMoneyActivity applyReturnMoneyActivity); //申请退款

    void inject(ModifyPhoneNumberActivity modifyPhoneNumberActivity);

    void inject(MoneyChangeActivity moneyChangeActivity);

    void inject(MyInfoActivity activity);

    void inject(CommonOrderDetailsActivity activity);
}
