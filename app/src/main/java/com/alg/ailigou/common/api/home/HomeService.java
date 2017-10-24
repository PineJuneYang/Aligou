package com.alg.ailigou.common.api.home;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommentModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.CommodityZoneModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.HomeLigouPaperDataModel;
import com.alg.ailigou.common.model.HomeWineDataModel;
import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.common.model.WeekNewGoodsDataModel;
import com.alg.ailigou.common.net.NetResult;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.api.home
 * Created by Chris Chen on 2017/7/5 17:01.
 * Explain:首页网络请求接口
 */

public interface HomeService {

    //获取首页数据
    @POST(UrlConsts.HOME_GET_HOME_DATA)
    Call<NetResult<HomeDataModel>> getHomeData(@Body RequestBody body);

    //获取轮播图数据
    @POST(UrlConsts.HOME_GET_BANNERS_DATA)
    Call<NetResult<List<BannerModel>>> getBannerData(@Body RequestBody body);

    //获取爱利购快讯
    @POST(UrlConsts.HOME_GET_NEWS_LIST)
    Call<NetResult<PageModel<NewsModel>>> getNewsList(@QueryMap Map<String, Object> map);

    //获取商品列表
    @POST(UrlConsts.HOME_GET_GOODS_LIST)
    Call<NetResult<PageModel<CommodityModel>>> getGoodsList(@Body RequestBody body);

    //获取商品列表
    @POST(UrlConsts.HOME_GET_GOODS_LIST)
    Call<NetResult<List<CommodityModel>>> getSimpleCommodityList(@QueryMap Map<String, Object> map);

    //获取商品专区2数据
    @POST(UrlConsts.HOME_GET_COMMODITY_ZONE_DATA)
    Call<NetResult<CommodityZoneModel>> getCommodityZoneData(@QueryMap Map<String, Object> map);

    //获取商品分类列表
    @POST(UrlConsts.CLASSIFICATION_GET_GOODS_TYPE_PAGE)
    Call<NetResult<List<HomeCommodityTypeModel>>> getHomeCommodityTypeList(@QueryMap Map<String, Object> map);

    //获取天天特价列表
    @POST(UrlConsts.HOME_GET_EVERYDAY_CHEAP_LIST)
    Call<NetResult<List<CommodityModel>>> getEverydayCheapList(@QueryMap Map<String, Object> map);//获取天天特价列表

    //获取爱利购精选推荐的三个值
    @POST(UrlConsts.HOME_GET_ALG_CHOIVE_RECOMMEND_LIST)
    Call<NetResult<List<HomeCommodityTypeModel>>> getAlgChoiceRecommendList(@QueryMap Map<String, Object> map);

    //获取爱利购精选列表
    @POST(UrlConsts.HOME_GET_ALG_CHOIVE_LIST)
    Call<NetResult<List<CommodityModel>>> getAlgChoiceList(@QueryMap Map<String, Object> map);

    //获取佳酿的数据
    @POST(UrlConsts.HOME_WINE_DATA)
    Call<NetResult<HomeWineDataModel>> getHomeWineData(@Body RequestBody body);

    //获取新品的数据
    @POST(UrlConsts.HOME_GET_NEW_GOODS_LIST)
    Call<NetResult<PageModel<CommodityModel>>> getNewGoodsList(@Body RequestBody body);  //获取新品的数据

    //获取利购券商城
    @POST(UrlConsts.HOME_GET_LIGOU_PAPER_DATA)
    Call<NetResult<HomeLigouPaperDataModel>> getLigouPaperData(@Body RequestBody body);

    //获取利购券商城
    @POST(UrlConsts.HOME_GET_GOODS_DATA)
    Call<NetResult<SelfGoodsDataModel>> getSelfGoodsData(@Body RequestBody body);

    //获取搜索商品详情
    @POST(UrlConsts.HOME_GET_SEARCH_GOODS_DETAIL)
    Call<NetResult<PageModel<CommodityModel>>> getSearchGoodsDetail(@Body RequestBody requestBody);

    //获取热门商品的数据
    @POST(UrlConsts.HOME_GET_SEARCH_HOT_GOODS)
    Call<NetResult<List<String>>> getSearchHotGoods(@Body RequestBody requestBody);


    //获取热销榜列表
    @POST(UrlConsts.HOME_GET_HOT_SALE_LIST)
    Call<NetResult<HomeHotSaleCommodityModel>> getHotSaleList(@Body RequestBody requestBody);

    //获取列表
    @POST(UrlConsts.HOME_GET_WEEK_NEW_GOODS)
    Call<NetResult<WeekNewGoodsDataModel>> getWeekNewGoodsData(@Body RequestBody requestBody);

    //爆款推荐
    @POST(UrlConsts.HOME_GET_HOT_RECOMMEND_DATA)
    Call<NetResult<HotRecommendDataModel>> getHotRecommendData(@Body RequestBody requestBody);

    //精选特价
    @POST(UrlConsts.HOME_GET_CHOICE_CHEAP_GOODS_DATA)
    Call<NetResult<ChoiceCheapGoodsDataModel>> getChoiceCheapGoodsData(@Body RequestBody requestBody);

    //获取商品详情数据
    @POST(UrlConsts.HOME_GET_GOODS_DETAILS_DATA)
    Call<NetResult<CommodityModel>> getGoodsDetailsData(@Body RequestBody requestBody);

    //取订单详情
    @POST(UrlConsts.HOME_GET_ORDER_DETAILS_DATA)
    Call<NetResult<OrderDetailsDataModel>> getOrderDetailsData(@Body RequestBody requestBody);

    //获取商品评论页面数据
    @POST(UrlConsts.HOME_GET_COMMENT_DATA)
    Call<NetResult<CommentDataModel>> getCommentData(@Body RequestBody requestBody);

    //获取商品评论分页
    @POST(UrlConsts.HOME_GET_COMMENT_PAGE)
    Call<NetResult<PageModel<CommentModel>>> getCommentPage(@Body RequestBody requestBody);

    //获取商品属性数据
    @POST(UrlConsts.HOME_GET_GOODS_ATTRIBUTE_DATA)
    Call<NetResult<List<String>>> getGoodsAttribute(@Body RequestBody requestBody);  //获取商品属性数据
    //在商品详情内添加收藏
    @POST(UrlConsts.HOME_ADD_GOODS_TO_FAV)
    Call<NetResult<Object>> addGoodsToFav(@Body RequestBody requestBody);
    //在商品详情内添加收藏
    @POST(UrlConsts.HOME_ADD_GOODS_TO_CART)
    Call<NetResult<Object>> addGoodsToCart(@Body RequestBody requestBody);


}
