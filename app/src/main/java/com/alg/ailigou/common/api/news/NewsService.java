package com.alg.ailigou.common.api.news;

import com.alg.ailigou.common.api.url.UrlConsts;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetResult;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * com.alg.ailigouapp.common.api.news
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:56
 * Explain:News模块网络请求
 */

public interface NewsService {
    /**
     * 获取新闻列表
     *
     * @param requestBody
     * @return
     */
    @POST(UrlConsts.HOME_GET_NEWS_LIST)
    Call<NetResult<PageModel<NewsModel>>> getNewsList(@Body RequestBody requestBody);

}

