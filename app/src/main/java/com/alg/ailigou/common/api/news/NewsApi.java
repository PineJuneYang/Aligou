package com.alg.ailigou.common.api.news;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetClient;
import com.alg.ailigou.common.net.NetManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:55
 * Explain:News模块Api
 */

public class NewsApi extends NetApi implements UrlConsts {
    private static NewsService service1 = NetClient.getRetrofit(NEWS_BASE_URL).create(NewsService.class);
    private static NewsService service2 = NetClient.getRetrofit(HOME_BASE_URL).create(NewsService.class);

    //获取爱利购快讯数据
    public static void getNewsList(int type,int page, int pageSize, NetCallback<PageModel<NewsModel>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        map.put("page",page);
        map.put("pageSize",pageSize);
        Call call = service2.getNewsList(postRequestBody(map));
        call.enqueue(callback);
        NetManager.addRequest(call);
    }

}
