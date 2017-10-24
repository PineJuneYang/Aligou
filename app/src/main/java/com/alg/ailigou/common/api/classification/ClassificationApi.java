package com.alg.ailigou.common.api.classification;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityTypeModel;
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
 * Explain:Classification模块Api
 */

public class ClassificationApi extends NetApi implements UrlConsts {
    private static ClassificationService service = NetClient.getRetrofit(CLASSIFICATION_BASE_URL).create(ClassificationService.class);

    //获取商品分类分页   recommendType;//用于区分请求 新品专场 和  好货榜单   1表示新品专场  2表示好货榜单
    public static void getGoodsTypePage(long type,int page,int pageSize,int recommendType, NetCallback<PageModel<CommodityTypeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("recommendType", recommendType);
        Call call = service.getGoodsTypePage(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    public static void getGoodsGroup(long type,int page,int pageSize,int recommendType, NetCallback<PageModel<CommodityTypeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("pageSize", pageSize);
//        map.put("recommendType", recommendType);
        Call call = service.getGoodsGroup(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }



    public static void getGoodsCategory(NetCallback<List<CommodityTypeModel>> callback){

        Map<String, Object> map = new HashMap<>();
        Call call = service.getGoodsCategory(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }



}
