package com.alg.ailigou.pages.news.fragments.newrelease;

import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/12.
 * 此类或接口用于
 */

public class NewRealeseContrats {
    interface View extends BaseView {
        void adapterNotifity(List<NewsModel> newsModels);
    }

    interface Presenter extends BasePresenter<View> {
        void loadNewsList(int type, int page, int pageSize);
    }
}
