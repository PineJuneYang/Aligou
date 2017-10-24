package com.alg.ailigou.common.api.union;

import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.model.UnionTypeModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;
import com.alg.ailigou.common.requestmodel.SearchUnionRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.alg.ailigou.common.api.url.UrlConsts.UNION_BASE_URL;
import static com.alg.ailigou.common.net.NetApi.postRequestBody;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于 联盟商家
 */

public class UniconApi {
    private static UniconService service = NetClient.getRetrofit(UNION_BASE_URL).create(UniconService.class);

    /**
     * 获取来联盟商家列表
     *
     * @param
     * @return
     */
    public static void getUnionStoreList(String keyWords, int page, int pageSize, NetCallback<PageModel<UnionModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyWords", keyWords);
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getUnionStoreList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    /**
     * 获取来联盟商家详情
     *
     * @param
     * @return
     */
    public static void getUnionDetailsData(long id, NetCallback<UnionModel> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Call call = service.getUnionDetailsData(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    /**
     * 获取来联盟商家详情
     *
     * @param
     * @return
     */
    public static void getCityList(int parentId, NetCallback<List<CityModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", parentId);
        Call call = service.getCityList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    /**
     * 获取来联盟商家分类
     *
     * @param
     * @return
     */
    public static void getUnionTypeList(NetCallback<List<UnionTypeModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        Call call = service.getUnionTypeList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    /**
     * 获取搜索后的联盟商家列表
     *
     * @param
     * @return
     */
    public static void  getSearchUnionStoreList (SearchUnionRequest request, NetCallback<PageModel<UnionModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("unionName", request.unionName);
        map.put("typeId", request.typeId);
        map.put("areaId", request.areaId);
        map.put("sort", request.sort);
        map.put("page", request.page);
        map.put("pageSize", request.pageSize);
        Call call = service.getSearchUnionStoreList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

}
