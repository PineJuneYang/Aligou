package com.alg.ailigou.pages.home.searchgoods;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/27
 * 此类或接口用于
 */

public interface SearchGoodsContracts {

    interface View extends BaseView{

       void loadHotGoods(List<String> hotGoods);

        void updateHistoryWords(List<String> historyWords);
    }


    interface  Presenter extends BasePresenter<View>{

        //加载热门商品
        void getHotGoods();

        //加载历史搜索
        void getHistoryGoods();



    }
}
