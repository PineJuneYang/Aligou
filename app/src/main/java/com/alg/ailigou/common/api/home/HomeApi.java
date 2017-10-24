package com.alg.ailigou.common.api.home;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommentModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.HomeLigouPaperDataModel;
import com.alg.ailigou.common.model.HomeWineDataModel;
import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.common.model.WeekNewGoodsDataModel;
import com.alg.ailigou.common.net.NetApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.api.home
 * Created by Chris Chen on 2017/7/5 17:09.
 * Explain:首页api
 */

public class HomeApi extends NetApi implements UrlConsts {
    private static HomeService service = NetClient.getRetrofit(HOME_BASE_URL).create(HomeService.class);

    //获取首页数据
    public static void getHomeData(NetCallback<HomeDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getHomeData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取轮播广告数据
    public static void getBannerData(int type, NetCallback<List<BannerModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        Call call = service.getBannerData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品分类列表
    public static void getGoodsList(int type, int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getGoodsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取首页简单商品列表
    public static void getSimpleCommodityList(int type, NetCallback<List<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        Call call = service.getSimpleCommodityList(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品分类列表
    public static void getEverydayCheapList(NetCallback<List<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getEverydayCheapList(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取热销榜列表
//    public static void getHotSaleList(NetCallback<List<CommodityModel>> callback) {
//        Map<String, Object> map = new HashMap<>();
//        Call call = service.getHotSaleList(requestMap(map));
//        call.enqueue(callback);
//        NetManager.addRequest(call);
//    }

    //获取爱利购精选列表
    public static void getAlgChoiceList(NetCallback<List<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getAlgChoiceList(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取爱利购精选三个按钮的数据
    public static void getAlgChoiceRecommendList(NetCallback<List<HomeCommodityTypeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getAlgChoiceRecommendList(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


    //获取每周热卖
    public static void getHotSaleFragmentWeek(NetCallback<List<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getAlgChoiceList(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取佳酿数据
    public static void getHomeWineData(NetCallback<HomeWineDataModel> callback) {
        Map<String, Object> map = new HashMap();
        Call call = service.getHomeWineData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取新品数据
    public static void getNewGoodsList(int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getNewGoodsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取自营数据
    public static void getSelfGoodsData(NetCallback<SelfGoodsDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getSelfGoodsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取利购券商城数据
    public static void getLigouPaperData(int page, int pageSize, NetCallback<HomeLigouPaperDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getLigouPaperData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


    //获取搜索详情数据
    public static void getSearchGoodsList(String keyWords, int sort, int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback) {

        Map<String, Object> map = new HashMap<>();
        map.put("keyWords", keyWords);
        map.put("sort", sort);
        map.put("page", page);
        map.put("pageSize", pageSize);

//        Call call = service.getLigouPaperData(postRequestBody(map));

        Call call1 = service.getSearchGoodsDetail(postRequestBody(map));
        call1.enqueue(callback);

        NetManager.addRequest(call1);
    }

    //获取热词搜索
    public static void getSearchHotGoodsList(int type, String keyWords, NetCallback<List<String>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("keyWords", "");
        Call call1 = service.getSearchHotGoods(postRequestBody(map));
        call1.enqueue(callback);
        NetManager.addRequest(call1);
    }

    //获取热销榜单
    public static void getHotSaleGoodsList(int page, int pageSize, NetCallback<HomeHotSaleCommodityModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getHotSaleList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


    //获取本周上新
    public static void getWeekNewGoodsData(int page, int pageSize, NetCallback<WeekNewGoodsDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getWeekNewGoodsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //爆款推荐
    public static void getHotRecommendData(int page, int pageSize, NetCallback<HotRecommendDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getHotRecommendData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //精选特价
    public static void getChoiceCheapGoodsData(int page, int pageSize, NetCallback<ChoiceCheapGoodsDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getChoiceCheapGoodsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品详情
    public static void getGoodsDetailsData(long id, NetCallback<CommodityModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);//商品id
        Call call = service.getGoodsDetailsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取订单详情
    public static void getOrderDetailsData(long id, NetCallback<OrderDetailsDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);//商品id
        Call call = service.getOrderDetailsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品评论页面数据
    public static void getCommentData(long id, NetCallback<CommentDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);//商品id
        Call call = service.getCommentData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品评论分页
    public static void getCommentPage(long id, int page, int pageSize, NetCallback<PageModel<CommentModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);//商品id
        map.put("page", page);//分页
        map.put("pageSize", pageSize);//页面大小
        Call call = service.getCommentPage(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商品评论分页
    public static void getGoodsAttribute(long id, NetCallback<List<String>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("goodsId", id);//商品id
        Call call = service.getGoodsAttribute(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //在商品详情内添加收藏
    public static void addGoodsToFav(long id, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);//商品id
        Call call = service.addGoodsToFav(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    //在商品详情内添加收藏
    public static void addGoodsToCart(CommodityModel goods, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", goods.id);//商品id
        map.put("cartCount", goods.cartCount);//商品id
        Call call = service.addGoodsToCart(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

}
