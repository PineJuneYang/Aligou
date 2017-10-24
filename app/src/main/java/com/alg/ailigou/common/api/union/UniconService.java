package com.alg.ailigou.common.api.union;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.model.UnionTypeModel;
import com.alg.ailigou.common.net.NetResult;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于
 */

public interface UniconService {
    /**
     * 获取来联盟商家列表
     *
     * @param requestBody
     * @return
     */
    @POST(UrlConsts.UNION_GET_UNION_PAGE)
    Call<NetResult<PageModel<UnionModel>>> getUnionStoreList(@Body RequestBody requestBody);

    /**
     * 获取来联盟商家详情
     *
     * @param requestBody
     * @return
     */
    @POST(UrlConsts.UNION_GET_DETAIL_DATA)
    Call<NetResult<UnionModel>> getUnionDetailsData(@Body RequestBody requestBody);



    //获取省/市/区 列表
    @POST(UrlConsts.UNION_GET_CITY_LIST)
    Call<NetResult<List<CityModel>>> getCityList(@Body RequestBody requestBody);

    //获取来联盟商家分类
    @POST(UrlConsts.UNION_GET_UNION_TYPE_LIST)
    Call<NetResult<List<UnionTypeModel>>> getUnionTypeList(@Body RequestBody requestBody);

    //获取来联盟商家分类
    @POST(UrlConsts.UNION_GET_SEARCH_UNION_LIST)
    Call<NetResult<PageModel<UnionModel>>> getSearchUnionStoreList(@Body RequestBody requestBody);
}
