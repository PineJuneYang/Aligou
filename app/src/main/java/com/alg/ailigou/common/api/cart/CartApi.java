package com.alg.ailigou.common.api.cart;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:55
 * Explain:Cart模块Api
 */

public class CartApi extends NetApi implements UrlConsts {
    private static CartService service = NetClient.getRetrofit(CART_BASE_URL).create(CartService.class);

    /**
     * 获取购物车列表
     *
     * @param
     * @return
     */
    public static void getCartInfoList(NetCallback<CartDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getCartData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 删除购物车
     *
     * @param
     * @return
     */
    public static void deleteCart(List<Long> list, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", list);
        Call call = service.deleteCart(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    /**
     * 把购物车商品移到收藏夹
     *
     * @param
     * @param list
     * @return
     */
    public static void moveGoodsToFav(List<Long> list, NetCallback<Object> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", list);
        Call call = service.moveGoodsToFav(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }




    //获取新品推送
    public static void getNewGoodsPush(int page, int pageSize, NetCallback<PageModel<CommodityModel>> callback){

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getNewGoodsPushData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


}
