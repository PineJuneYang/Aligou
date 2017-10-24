package com.alg.ailigou.common.api.ailigou;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * AiligouApp
 * com.alg.ailigou.common.api.ailigou
 * Created by Chris Chen on 2017/7/22 10:50.
 * Explain:爱利购共用的接口
 */

public interface AiligouService {
    //文件上传
    @Multipart
    @POST(UrlConsts.AILIGOU_COMMON_UPLOAD)
    Call<NetResult<Object>> upload(@Part MultipartBody.Part part);

    @POST(UrlConsts.AILIGOU_COMMON_UPLOAD_IMAGES)
    Call<NetResult<Object>> submitFiles(@Body MultipartBody files);

    //获取推荐列表
    @POST(UrlConsts.AILIGOU_COMMON_GET_RECOMMEND_LIST)
    Call<NetResult<PageModel<CommodityModel>>> getRecommendList(@Body RequestBody body);

}
