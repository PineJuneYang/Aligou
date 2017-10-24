package com.alg.ailigou.common.api.mall;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.MallDataModel;
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
 * com.alg.ailigou.common.api.mall
 * Created by Chris Chen on 2017/7/21 17:47.
 * Explain:商城
 */

public class MallApi extends NetApi implements UrlConsts {
    private static MallService service = NetClient.getRetrofit(HOME_BASE_URL).create(MallService.class);

    //获取商城页面数据
    public static void getMallData(NetCallback<MallDataModel> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getMallData(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取商城列表数据
    public static void getGoodsListFragmentData(NetCallback<List<CommodityModel>> callback){
        Map<String, Object> map = new HashMap<>();
        Call call = service.getMallGoodsListFragment(requestMap(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }


}
