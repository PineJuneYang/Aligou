package com.alg.ailigou.common.api.ailigou;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.common.api.ailigou
 * Created by Chris Chen on 2017/7/22 10:49.
 * Explain:公用的接口
 */

public class AiligouApi extends NetApi implements UrlConsts {
    private static AiligouService service = NetClient.getRetrofit(HOME_BASE_URL).create(AiligouService.class);

    //文件上传
    public static void upload(File file, NetCallback<Object> callback) {
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);
        Call call = service.upload(part);
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
    //多文件上传
    public static void submitFiles(List<File> files, NetCallback<Object> callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            String name = file.getName();
            builder.addFormDataPart(name.substring(0, name.length() - 4), name.substring(0, name.length() - 4), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        Call call = service.submitFiles(multipartBody);
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

    //获取推荐列表
    public static void getRecommendList(int type,int page,int pageSize,NetCallback<PageModel<CommodityModel>> callback) {
        Map<String, Object> map = new HashMap();
        map.put("type", type);
        map.put("page", page);
        map.put("pageSize", pageSize);
        Call call = service.getRecommendList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }
}
