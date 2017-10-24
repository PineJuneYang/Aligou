package com.alg.ailigou.common.api.mall;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.MallDataModel;
import com.alg.ailigou.common.net.NetResult;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * AiligouApp
 * com.alg.ailigou.common.api.mall
 * Created by Chris Chen on 2017/7/21 17:45.
 * Explain:商城
 */

public interface MallService {
    //获取商城页面数据
    @POST(UrlConsts.MALL_GET_MALL_DATA)
    Call<NetResult<MallDataModel>> getMallData(@QueryMap Map<String, Object> map);


    //获取商城列表fragment里面的数据
    @POST(UrlConsts.MALL_GET_GOODS_LIST_DATA)
    Call<NetResult<List<CommodityModel>>> getMallGoodsListFragment(@QueryMap Map<String, Object> map);

}
