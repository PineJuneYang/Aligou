package com.alg.ailigou.pages.union.entrance;

import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.requestmodel.SearchUnionRequest;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.union
 * Created by Chris Chen on 2017/7/24 10:18.
 * Explain:联盟商家
 */

public class UnionContracts {
    interface View extends BaseView {
        void notify(PageModel<UnionModel> pageModel);

    }

    interface Presenter extends BasePresenter<View> {
        void loadData(String keyWords);

        /**
         * 这个是搜索结果 在 unionActivity 里面调用
         * @param request
         */
        void loadSearchData(SearchUnionRequest request);
    }
}
