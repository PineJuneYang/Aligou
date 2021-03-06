package com.alg.ailigou.pages.news.entrance;

import com.alg.ailigou.common.api.news.NewsApi;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/12.
 * 此类或接口用于
 */

public class NewsPresenter implements NewsContrats.Presenter {
    private NewsContrats.View view;

    @Inject
    public NewsPresenter() {
    }

    @Override
    public void bindView(NewsContrats.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }

    @Override
    public void loadNewsList(int type,int page,int pageSize) {
        NewsApi.getNewsList(type,page,pageSize, new NetCallback<PageModel<NewsModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<NewsModel>> netResponse) {
                if (netResponse.isSuccess)
                view.adapterNotifity(netResponse.data.dataList);
            }
        });
    }
}
