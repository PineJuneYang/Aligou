package com.alg.ailigou.common.api.user;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.CommonTypeModel;
import com.alg.ailigou.common.model.LigouDetailDataModel;
import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.common.model.MyBillData;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.net.NetResult;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:56
 * Explain:User模块网络请求
 */

public interface UserService {

    /**
     * 获取注册号码的验证码
     *
     * @param body
     * @return
     */
    @POST(UrlConsts.USER_GET_VERIFICATION_CODE)
    Call<NetResult<Object>> getVCode(@Body RequestBody body);


    @POST(UrlConsts.USER_REGISTER)
    Call<NetResult> register(@Body RequestBody body);


    @POST(UrlConsts.USER_LOGIN)
    Call<NetResult<String>> login(@Body RequestBody body);


    @POST(UrlConsts.USER_RESET_SAVE)
    Call<NetResult> resetSave(@Body RequestBody body);


    @POST(UrlConsts.USER_SIGN_OUT)
    Call<NetResult> signLogin(@Body RequestBody body);


    @POST(UrlConsts.USER_GET_ADDRESS)
    Call<NetResult<List<ShippingAddressModel>>> getReceiveAddressList(@Body RequestBody body);


    @POST(UrlConsts.USER_UPDATE_ADDRESS)
    Call<NetResult> updateAddress(@Body RequestBody body);

    /**
     * 解绑银行卡
     *
     * @param body
     * @return
     */
    @POST(UrlConsts.USER_REMOVE_BANK_CARD)
    Call<NetResult> removeBankCard(@Body RequestBody body);

    /**
     * 添加或修改
     *
     * @param body
     * @return
     */
    @POST(UrlConsts.USER_UPDATE_BANK_CARD)
    Call<NetResult> updateBankCard(@Body RequestBody body);

    /**
     * 利购券兑换记录
     */
    @POST(UrlConsts.USER_LIGOU_CHANGE_NOTES)
    Call<NetResult> getLigouExchangeList(@Body RequestBody body);

    /**
     * 利购券兑换余额
     */
    @POST(UrlConsts.USER_LIGOU_CHANGE_OVERAGE)
    Call<NetResult<PageModel<LigouOverageModel>>> getLigouOverageList(@Body RequestBody body);

    @POST(UrlConsts.USER_QUERY_ONLINE_ORDER)
    Call<NetResult<PageModel<OrderDetailsDataModel>>> queryOnlineOrder(@Body RequestBody body);

    @POST(UrlConsts.USER_QUERY_OFFLINE_ORDER)
    Call<NetResult<PageModel<OrderOffLineDataModel>>> queryOfflineOrder(@Body RequestBody body);

    /**
     * 我的订单(包括全部,待付款等)
     *
     * @param body
     * @return
     */
    @POST(UrlConsts.USER_GET_ORDER_LIST)
    Call<NetResult<PageModel<OrderDetailsDataModel>>> getOrderDetailsList(@Body RequestBody body);

    /**
     * 退货退款
     */
    @POST(UrlConsts.USER_REFUNDS_AFTERSALE)
    Call<NetResult<PageModel<ReturnGoodsData>>> getRefundsAfterSaleList(@Body RequestBody body);

    /**
     * 利购券详情
     */
    @POST(UrlConsts.USER_LIGOU_DETAIL_DATA)
    Call<NetResult<LigouDetailDataModel>> getLigouDetailsData(@Body RequestBody body);

    /**
     * 修改登录密码
     * oldPassword;  //旧密码
     * newPassword;  //新密码
     */

    @POST(UrlConsts.USER_UPDATA_LOGIN_PWD)
    Call<NetResult> updatePassword(@Body RequestBody body);

    /**
     * 修改手机号码
     */

    @POST(UrlConsts.USER_UPDATA_PHONE_NUMBER)
    Call<NetResult> updatePhone(@Body RequestBody body);

    /**
     * 校验手机号码
     */
    @POST(UrlConsts.USER_EXIST_PHONE)
    Call<NetResult> existPhone(@Body RequestBody body);

    /**
     * 修改支付密码
     */
    @POST(UrlConsts.USER_UPDATE_PAY_PASSWORD)
    Call<NetResult> updatePayPassword(@Body RequestBody body);

    /**
     * 删除收货地址
     */
    @POST(UrlConsts.USER_DELETE_ADDRESS)
    Call<NetResult> deleteReceiveAddress(@Body RequestBody body);

    /**
     * 收藏的新闻
     */
    @POST(UrlConsts.USER_GET_COLLECTION_NEWS)
    Call<NetResult> getCollectionNewsList(@Body RequestBody body);

    /**
     * 删除收藏的新闻
     */
    @POST(UrlConsts.USER_DELETE_COLLECTION_NEWS)
    Call<NetResult> deleteCollectionNews(@Body RequestBody body);

    /**
     * 收藏的商品
     */
    @POST(UrlConsts.USER_GET_COLLECTION_GOODS)
    Call<NetResult<PageModel<CommodityModel>>> getCollectionGoodsList(@Body RequestBody body);

    /**
     * 删除 收藏的商品
     */
    @POST(UrlConsts.USER_DELETE_COLLECTION_GOODS)
    Call<NetResult> deleteCollectionGoods(@Body RequestBody body);

    /**
     * 收藏的商户
     */
    @POST(UrlConsts.USER_GET_COLLECTION_UNION)
    Call<NetResult<PageModel<UnionModel>>> getCollectionUnionList(@Body RequestBody body);

    /**
     * 删除收藏的商户
     */
    @POST(UrlConsts.USER_DELETE_COLLECTION_UNIONS)
    Call<NetResult> deleteCollectionUnions(@Body RequestBody body);

    /**
     * 我的足迹
     */
    @POST(UrlConsts.USER_GET_MY_FOOT)
    Call<NetResult<PageModel<CommodityModel>>> getFootprintsList(@Body RequestBody body);

    /**
     * 清空我的足迹
     */
    @POST(UrlConsts.USER_DELETE_MY_FOOT)
    Call<NetResult> deleteAllFootprints(@Body RequestBody body);

    /**
     * 提交评价
     */
    @POST(UrlConsts.USER_SEND_COMMENT)
    Call<NetResult> sendCommentData(@Body RequestBody body);

    /**
     * 获取物流信息
     */
    @POST(UrlConsts.USER_GET_LOGISTICS_DATA)
    Call<NetResult<LogisticsModel>> getLogisticsData(@Body RequestBody body);

    /**
     * 获取个人信息
     */
    @POST(UrlConsts.USER_GET_DATA)
    Call<NetResult<UserModel>> getUserData(@Body RequestBody body);

    /**
     * 获取我的账单
     */
    @POST(UrlConsts.USER_GET_MY_BILL)
    Call<NetResult<MyBillData>> getMyBill(@Body RequestBody body);

    /**
     * 资金变动记录
     */
    @POST(UrlConsts.USER_MONEY_EXCHANGE)
    Call<NetResult<PageModel<MoneyChangeModel>>> getMoneyExchangeList(@Body RequestBody body);

    /**
     * 修改个人资料
     */
    @POST(UrlConsts.USER_UPDATA_USER_INFO)
    Call<NetResult> updataUserInfo(@Body RequestBody body);

    /**
     * 提现
     */
    @POST(UrlConsts.USER_WITHDRAW)
    Call<NetResult> exchangeMoney(@Body RequestBody body);

    /**
     * 退货退款
     */
    @POST(UrlConsts.USER_RETURN_GOODS)
    Call<NetResult<ReturnGoodsData>> returnGoods(@Body RequestBody body);

    /**
     * 取消订单
     */
    @POST(UrlConsts.USER_DELETE_ORDER)
    Call<NetResult> deleteOrder(@Body RequestBody body);

    /**
     * 确认收货
     */
    @POST(UrlConsts.USER_SURE_RECEIVE_GOODS)
    Call<NetResult> confirmReceiveGoods(@Body RequestBody body);

    /**
     * 第三方登陆
     */
    @POST(UrlConsts.USER_THIRD_LOGIN)
    Call<NetResult> thirdLogin(@Body RequestBody body);

    /**
     * 账户校验(修改手机号码时候验证码手机校验)
     */
    @POST(UrlConsts.USER_VERIFY_ID)
    Call<NetResult> verifyID(@Body RequestBody body);

    /**
     * 自动登录 (使用token)
     */
    @POST(UrlConsts.USER_AUTO_LOGIN)
    Call<NetResult> autoLogin(@Body RequestBody body);

    /**
     * 资金变动记录变动类型,就是type值
     */
    @POST(UrlConsts.USER_MONEY_CHANGE_TYPE_LIST)
    Call<NetResult<List<CommonTypeModel>>> getMoneyExchangeTypeList(@Body RequestBody body);
    /**
     * 退款详情
     */
    @POST(UrlConsts.USER_RETURN_GOODS_DETAILS)
    Call<NetResult<ReturnGoodsData>> retReturnGoodsData(@Body RequestBody body);

    /**
     * 图片上传
     */
    @POST(UrlConsts.AILIGOU_COMMON_UPLOAD_IMAGES)
    Call<NetResult<String>> submitFiles(@Body MultipartBody files);
}

