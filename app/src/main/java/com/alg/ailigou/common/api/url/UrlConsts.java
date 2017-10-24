package com.alg.ailigou.common.api.url;

/**
 * com.alg.ailigouapp.common.api.url
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:54
 * Explain:网络请求url
 */

public interface UrlConsts {
    // region BaseUrl
    // String BASE_URL = "http://192.168.1.107:8080/ailigou/";//Chris Chen JavaWeb Mock API
   String BASE_URL = "http://192.168.1.252/ailigou/v1/";//Test Server API
    //  String BASE_URL = "http://192.168.1.104:8082/v1/";//刘海敏地址//
//    String BASE_URL = "http://192.168.1.122:8080/v1/";//张佳楠

//String BASE_URL = "http://192.168.1.115:8080/ailigou/v1/";
    String AILIGOU_BASE_URL = BASE_URL + "ailigou/";//共用接口
    String HOME_BASE_URL = BASE_URL + "home/";//首页
    String CLASSIFICATION_BASE_URL = BASE_URL + "classic/";//分类
    String NEWS_BASE_URL = BASE_URL + "news/";//新闻
    String CART_BASE_URL = BASE_URL + "cart/";//购物车
    String USER_BASE_URL = BASE_URL + "user/";//用户
    String UNION_BASE_URL = BASE_URL + "union/";//联盟商家
    // endregion

    //region Common 公共
    String AILIGOU_COMMON_UPLOAD = "upload";//上传文件
    String AILIGOU_COMMON_UPLOAD_IMAGES = "fileUpload";//上传图片文件
    String AILIGOU_COMMON_GET_RECOMMEND_LIST = "getRecommendList";//猜你喜欢
    //endregion

    //region Start 启动

    //endregion

    //region Main 主界面

    //endregion

    // region Home 首页
    String HOME_GET_HOME_DATA = "getHomeData";//获取首页数据(todo:已通过)
    String HOME_GET_BANNERS_DATA = "getBannerData";//01.广告轮播图数据(todo:已通过)
    String HOME_GET_NEWS_LIST = "getNewsList";//02.获取新闻列表(todo:已通过)
    String HOME_GET_GOODS_LIST = "getGoodsList";//03.获取商品列表(todo:已通过)
    String HOME_GET_COMMODITY_ZONE_DATA = "getCommodityZoneData";//04.获取商品专区2数据(todo:已通过)
    String HOME_GET_EVERYDAY_CHEAP_LIST = "getEverydayCheapList";//获取天天特价列表
    String HOME_GET_HOT_SALE_LIST = "getHotSaleGoodsList";//获取热销榜列表
    String HOME_GET_ALG_CHOIVE_RECOMMEND_LIST = "getAlgChoiceRecommendList";//爱利购精选 获取三个
    String HOME_GET_ALG_CHOIVE_LIST = "getAlgChoiceList";//爱利购精选 列表
    String HOME_WINE_DATA = "getWineData";//佳酿数据
    String HOME_GET_NEW_GOODS_LIST = "getNewGoodsList";//新品
    String HOME_GET_LIGOU_PAPER_DATA = "getLigouPaperData";//利购券商城
    String HOME_GET_GOODS_DATA = "getSelfGoodsData";//自营商品
    String HOME_GET_SEARCH_GOODS_DETAIL = "getSearchGoodsList";//搜索商品详情
    String HOME_GET_SEARCH_HOT_GOODS = "getSearchHotGoodsList";//搜索热门商品
    String HOME_GET_WEEK_NEW_GOODS = "getWeekNewGoodsData"; //本周上新
    String HOME_GET_HOT_RECOMMEND_DATA = "getHotRecommendData"; //爆款推荐
    String HOME_GET_CHOICE_CHEAP_GOODS_DATA = "getChoiceCheapGoodsData"; //精选特价
    String HOME_GET_GOODS_DETAILS_DATA = "getGoodsDetailsData"; //获取商品详情数据
    String HOME_GET_ORDER_DETAILS_DATA = "getOrderDetailsData"; //获取订单详情数据
    String HOME_GET_COMMENT_DATA = "getCommentData"; //获取商品评论页数据
    String HOME_GET_COMMENT_PAGE = "getCommentPage"; //获取商品评论分页数据
    String HOME_GET_GOODS_ATTRIBUTE_DATA = "getGoodsAttrSpec"; //获取商品属性数据
    String HOME_ADD_GOODS_TO_FAV = "addGoodsToFav"; //在商品详情内添加收藏
    String HOME_ADD_GOODS_TO_CART = "addGoodsToCart"; //在商品详情内添加购物车
    //endregion

    //region Classification 分类
    String CLASSIFICATION_GET_GOODS_TYPE_PAGE = "getGoodsTypePage";//05.获取商品分类分页(todo:已通过)
    String CLASSIFICATION_GET_GOODS_GROUP = "getGoodsGroup"; //获取商品分类;
    String CLASSIFICATION_GET_GOODS_CATEGORY = "getGoodsCategory"; //06.获取商品分类左侧的分类

    //endregion

    //region Supper 超级购

    //endregion

    //region News 新闻资讯

    //endregion

    //region cart 购物车
    String CART_DATA = "getCartData";//获取购物车数据
    String CART_DELETE_CART = "deleteCartGoods";//删除购物车
    String CART_NEWGOODSPUSH = "getNewGoodsPushData";  //获取新品推送
    String CART_MOVE_GODS_TO_FAV = "moveGoodsToFav";//把购物车商品移到收藏夹

    //endregion

    //region Personal 个人中心
    String USER_GET_VERIFICATION_CODE = "getVCode"; //获取验证码
    String USER_REGISTER = "register"; //注册
    String USER_LOGIN = "login";  //登录
    String USER_RESET_SAVE = "resetPwd";  //重置密码的保存
    String USER_SIGN_OUT = "logout";  //退出登录
    String USER_GET_ADDRESS = "getReceiveAddressList";//获取收货地址
    String USER_UPDATE_ADDRESS = "addOrUpdateReceiveAddress";//编辑或者新增地址
    String USER_REMOVE_BANK_CARD = "removeBankCard";//解绑银行卡
    String USER_UPDATE_BANK_CARD = "updateBankCard";//解绑银行卡
    String USER_LIGOU_CHANGE_NOTES = "getLigouExchangeList";//利购券兑换记录
    String USER_LIGOU_CHANGE_OVERAGE = "getLigouOverageList";//利购券余额
    String USER_GET_ORDER_LIST = "getOrderDetailsList";//我的订单(包括全部,待付款等)
    String USER_QUERY_ONLINE_ORDER = "getOnlineOrderList";  //查询线上订单
    String USER_QUERY_OFFLINE_ORDER = "getOfflineOrderList";  //查询线下订单
    String USER_REFUNDS_AFTERSALE = "getReturnGoodsList";//退款退货列表
    String USER_LIGOU_DETAIL_DATA = "getLigouDetailsData";//利购券详情
    String USER_UPDATA_LOGIN_PWD = "updatePassword";//修改登录密码
    String USER_UPDATA_PHONE_NUMBER = "updatePhone";//修改手机密码
    String USER_EXIST_PHONE = "existPhone";//检验手机号码是否已经使用
    String USER_UPDATE_PAY_PASSWORD = "updatePayPassword";//修改支付密码
    String USER_DELETE_ADDRESS = "deleteReceiveAddress";//删除收货地址
    String USER_GET_COLLECTION_NEWS = "getCollectionNewsList";//收藏的新闻
    String USER_GET_COLLECTION_GOODS = "getCollectionGoodsList";//收藏的商品
    String USER_GET_COLLECTION_UNION = "getCollectionUnionList";//收藏的上架
    String USER_GET_MY_FOOT = "getFootprintsList";//我的足迹
    String USER_DELETE_COLLECTION_NEWS = "deleteCollectionNews";//删除收藏新闻
    String USER_DELETE_COLLECTION_GOODS = "deleteCollectionGoods";//删除收藏的商品
    String USER_DELETE_COLLECTION_UNIONS = "deleteCollectionUnions";//删除收藏商家
    String USER_DELETE_MY_FOOT = "deleteAllFootprints";//清空我的足迹
    String USER_SEND_COMMENT = "sendCommentData";//提交评价
    String USER_GET_LOGISTICS_DATA = "getLogisticsData";//获取物流信息
    String USER_GET_DATA = "getUserData";//获取个人信息
    String USER_GET_MY_BILL = "getMyBillData";//获取我的账单
    String USER_MONEY_EXCHANGE = "getMoneyExchangeList";//资金变动记录
    String USER_UPDATA_USER_INFO = "updataUserInfo";//修改个人资料
    String USER_RETURN_GOODS = "returnGoods";//退款/退货
    String USER_WITHDRAW = "exchangeMoney";//提现
    String USER_SURE_RECEIVE_GOODS = "confirmReceiveGoods";//确认收货
    String USER_DELETE_ORDER = "deleteOrder";//取消订单
    String USER_THIRD_LOGIN = "thirdLogin";//第三方登陆
    String USER_AUTO_LOGIN = "autoLogin";//自动登录
    String USER_VERIFY_ID = "verifyID";//账户校验(修改手机号码时候验证码手机校验)
    String USER_MONEY_CHANGE_TYPE_LIST = "getMoneyExchangeTypeList";//资金变动记录变动类型,就是type值
    String USER_RETURN_GOODS_DETAILS ="retReturnGoodsData";//退款详情

    //联盟商家
    String UNION_GET_UNION_PAGE = "getUnionStoreList";//获取联盟商家列表
    String UNION_GET_DETAIL_DATA = "getUnionDetailsData";//获取联盟商家列表
    String UNION_GET_CITY_LIST = "getCityList"; //获取到省列表或城市列表或区列表
    String UNION_GET_UNION_TYPE_LIST = "getUnionTypeList"; //获取来联盟商家分类
    String UNION_GET_SEARCH_UNION_LIST = "getSearchUnionStoreList";//获取搜索联盟商家列表
    //endregion

    //region Mall 商城

    String MALL_GET_MALL_DATA = "getMallData";//01.获取商城页面数据
    String MALL_GET_GOODS_LIST_DATA = "getGoodsListData";

    //endregion

    //region test 测试
    String TEST = "test.php";
    String TEST_BODY = "test";
    //endregion
}
