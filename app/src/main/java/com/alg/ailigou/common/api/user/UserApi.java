package com.alg.ailigou.common.api.user;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.BankModel;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.CommonTypeModel;
import com.alg.ailigou.common.model.LigouDetailDataModel;
import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.common.model.MyBillData;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.net.NetApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:55
 * Explain:User模块Api
 */

public class UserApi extends NetApi implements UrlConsts {
    private static UserService service = NetClient.getRetrofit(USER_BASE_URL).create(UserService.class);

    //获取验证码
    public static void getVCode(String phoneNumber, int type, NetCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("type", type);
        Call call = service.getVCode(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }


    //注册
    public static void register(String phoneNumber, String vCode, String password, NetCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("vCode", vCode);
        map.put("password", password);

        Call call = service.register(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }


    //登录
    public static void login(String phoneNumber, String password, NetCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("password", password);
        Call call = service.login(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }


    //重置密码的保存
    public static void resetSave(String phoneNumber, String vCode, String password, NetCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("vCode", vCode);
        map.put("password", password);
        Call call = service.resetSave(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }

    //退出登录
    public static void signOut(NetCallback callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.signLogin(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }


    public static void getAddress(NetCallback<List<ShippingAddressModel>> callback) {

        Map<String, Object> map = new HashMap<>();
        Call call = service.getReceiveAddressList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);


    }


    public static void updateAddress(int type,
                                     String addressName,
                                     String addressPhone,
                                     CityModel provinceModel,
                                     CityModel cityModel,
                                     CityModel districtModel,
                                     String s5,
                                     boolean isDefault,
                                     NetCallback callback) {


        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("addressName", addressName);
        map.put("addressPhone", addressPhone);
        map.put("provinceModel", provinceModel);
        map.put("cityModel", cityModel);
        map.put("districtModel", districtModel);
        map.put("detailAddress", s5);
        map.put("isDefault", isDefault);
        Call call = service.updateAddress(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @param callback
     */
    public static void deleteReceiveAddress(long id, NetCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Call call = service.deleteReceiveAddress(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 解绑银行卡
     *
     * @param
     * @return
     */
    public static void removeBankCard(NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.removeBankCard(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 修改或添加银行卡
     *
     * @param
     * @return
     */
    public static void updateBankCard(BankModel bankModel, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", bankModel.name);
        map.put("bankNumber", bankModel.bankNumber);
        map.put("bankBranchAddress", bankModel.bankBranchAddress);
        map.put("bankName", bankModel.bankName);
        map.put("idNumber", bankModel.idNumber);
        Call call = service.updateBankCard(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 利购券兑换记录
     */
    public static void getLigouExchangeList(long timeFrom, long timeTo, int staus, int page, int pageSize, NetCallback<PageModel<LigouExchangeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", timeFrom);
        map.put("endTime", timeTo);
        map.put("type", staus);
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getLigouExchangeList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 利购券兑换记录
     */
    public static void getLigouOverageList(long timeFrom, long timeTo, int page, int pageSize, NetCallback<PageModel<LigouOverageModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", timeFrom);
        map.put("endTime", timeTo);
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getLigouOverageList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);


    }


    //查询线上订单
    public static void queryOnlineOrder(int page, int pageSize, long beginTime, long endTime, String status, NetCallback<PageModel<OrderDetailsDataModel>> callback) {

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("status", status);

        Call call = service.queryOnlineOrder(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);


    }


    //查询线下订单
    public static void queryOfflineOrder(int page, int pageSize, long beginTime, long endTime, NetCallback<PageModel<OrderOffLineDataModel>> callback) {

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);

        Call call = service.queryOfflineOrder(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }

    //我的订单(包括全部,待付款等)
    public static void getOrderDetailsList(int type, int page, int pageSize, NetCallback<PageModel<OrderDetailsDataModel>> callback) {

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("type", type);
        Call call = service.getOrderDetailsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }


    //退货退款列表
    public static void getRefundsAfterSaleList(int page, int pageSize, NetCallback<PageModel<ReturnGoodsData>> callback) {

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);

        Call call = service.getRefundsAfterSaleList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);

    }

    /**
     * 利购券详情
     */

    public static void getLigouDetailsData(NetCallback<LigouDetailDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getLigouDetailsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 修改登录密码
     * oldPassword;  //旧密码
     * newPassword;  //新密码
     */
    public static void updatePassword(String oldPwd, String newPwd, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPassword", oldPwd);
        map.put("newPassword", newPwd);
        Call call = service.updatePassword(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 修改手机号码
     */
    public static void updatePhone(String phoneNumber, String vCode, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("vCode", vCode);
        Call call = service.updatePhone(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 检验手机号码是否被使用
     */
    public static void existPhone(String phoneNumber, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        Call call = service.existPhone(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 修改支付密码
     * public String phoneNumber;  //电话号码
     * public String vCode;  //验证码
     * public String newPayPassword;  //密码
     */
    public static void updatePayPassword(String phoneNumber, String vCode, String newPayPassword, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("vCode", vCode);
        map.put("newPayPassword", newPayPassword);
        Call call = service.updatePayPassword(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取收藏的新闻数据
    public static void getCollectionNewsList(int page, int pageSize, NetCallback<PageModel<NewsModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getCollectionNewsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取收藏的商品
    public static void getCollectionGoodsList(int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getCollectionGoodsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取收藏的新闻商户
    public static void getCollectionUnionList(int page, int pageSize, NetCallback<PageModel<UnionModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getCollectionUnionList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取我的足迹
    public static void getFootprintsList(int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getFootprintsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 清空我的足迹
     */
    public static void deleteAllFootprints(NetCallback<PageModel<Object>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.deleteAllFootprints(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 删除我收藏的新闻
     */
    public static void deleteCollectionNews(List<Long> ids, NetCallback<PageModel<Object>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        Call call = service.deleteCollectionNews(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 删除我收藏的商品
     */
    public static void deleteCollectionGoods(List<Long> ids, NetCallback<PageModel<Object>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        Call call = service.deleteCollectionGoods(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 删除我收藏的商家
     */
    public static void deleteCollectionUnions(List<Long> ids, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        Call call = service.deleteCollectionUnions(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 提交评价
     */
    public static void sendCommentData(Map<String, Object> map, NetCallback<Object> callback) {
        if (map == null) {
            map = new HashMap<>();
        }
        Call call = service.sendCommentData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 获取物流信息
     * shipperCode:快递公司编号
     */
    public static void getLogisticsData(String shipperCode, String logisticCode, NetCallback<LogisticsModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("shipperCode", shipperCode);
        map.put("logisticCode", logisticCode);
        Call call = service.getLogisticsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 获取个人信息
     */
    public static void getUserData(NetCallback<UserModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getUserData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 获取我的账单
     */
    public static void getMyBill(NetCallback<MyBillData> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getMyBill(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 资金变动记录
     */
    public static void getMoneyExchangeList(int page, int pageSize, long startTime, long endTime, int type, NetCallback<PageModel<MoneyChangeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("type", type);
        Call call = service.getMoneyExchangeList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 修改个人资料
     */
    public static void updataUserInfo(int sex, String imageUrl, String name, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("sex", sex);
        map.put("imageUrl", imageUrl);
        map.put("name", name);
        Call call = service.updataUserInfo(postRequestBody(map));
        call.enqueue(callback);
    }

    /**
     * 提现
     */
    public static void exchangeMoney(double money, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        Call call = service.exchangeMoney(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 退货退款
     */
    public static void returnGoods(Map<String, Object> map, NetCallback<ReturnGoodsData> callback) {
        if (map == null) {
            map = new HashMap<>();
        }
        Call call = service.returnGoods(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 取消订单
     */
    public static void deleteOrder(long orderId, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        Call call = service.deleteOrder(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 确认收货
     */
    public static void confirmReceiveGoods(long orderId, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        Call call = service.confirmReceiveGoods(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 第三方登陆
     * public String uid;
     * public String name;
     * public int gender;//0  男   1女  2,保密
     * public String iconurl;//头像地址
     */
    public static void thirdLogin(String uid, String name, int gender, String iconurl, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("name", name);
        map.put("gender", gender);
        map.put("iconurl", iconurl);
        Call call = service.thirdLogin(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 自动登录
     */
    public static void autoLogin(NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.autoLogin(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 账户校验(修改手机号码时候验证码手机校验)
     */
    public static void verifyID(String phoneNumber, String vCode, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("vCode", vCode);
        Call call = service.verifyID(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


    //多文件上传
    public static void submitFiles(List<File> files, NetCallback<Object> callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            String name = file.getName();
            builder.addFormDataPart(name, name, requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        Call call = service.submitFiles(multipartBody);
        call.enqueue(callback);
    }

    /**
     * 资金变动记录变动类型,就是type值
     */
    public static void getMoneyExchangeTypeList(NetCallback<List<CommonTypeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getMoneyExchangeTypeList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 退款详情
     */
    public static void retReturnGoodsData(long orderId, NetCallback<ReturnGoodsData> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        Call call = service.retReturnGoodsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
}
