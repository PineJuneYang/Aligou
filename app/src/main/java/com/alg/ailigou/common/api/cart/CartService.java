package com.alg.ailigou.common.api.cart;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetResult;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:56
 * Explain:Cart模块网络请求
 */

public interface CartService {

    /**
     * 获取购物车列表
     *
     * @param
     * @return
     */
    @POST(UrlConsts.CART_DATA)
    Call<NetResult<CartDataModel>> getCartData(@Body RequestBody requestBody);

    /**
     * 删除购物车
     *
     * @param
     * @return
     */
    @POST(UrlConsts.CART_DELETE_CART)
    Call<NetResult<Object>> deleteCart(@Body RequestBody requestBody);

    /**
     * 把购物车商品移到收藏夹
     *
     * @param
     * @return
     */
    @POST(UrlConsts.CART_MOVE_GODS_TO_FAV)
    Call<NetResult<Object>> moveGoodsToFav(@Body RequestBody requestBody);

    /**
     *
     *
     * 获取新品推送
     *
     *
     */
    @POST(UrlConsts.CART_NEWGOODSPUSH)
    Call<NetResult<PageModel<CommodityModel>>> getNewGoodsPushData(@Body RequestBody requestBody);




}

