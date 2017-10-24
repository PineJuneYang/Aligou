package com.alg.ailigou.common.api.classification;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetResult;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:56
 * Explain:Classification模块网络请求
 */

public interface ClassificationService {
    //获取商品分类列表(第一个热门推荐)
    @POST(UrlConsts.CLASSIFICATION_GET_GOODS_TYPE_PAGE)
    Call<NetResult<PageModel<CommodityTypeModel>>> getGoodsTypePage(@Body RequestBody body);
    //获取商品分类列表
    @POST(UrlConsts.CLASSIFICATION_GET_GOODS_GROUP)
    Call<NetResult<PageModel<CommodityTypeModel>>> getGoodsGroup(@Body RequestBody body);
    //获取商品分类左侧的分类
    @POST(UrlConsts.CLASSIFICATION_GET_GOODS_CATEGORY)
    Call<NetResult<List<CommodityTypeModel>>> getGoodsCategory(@Body RequestBody body);





}

