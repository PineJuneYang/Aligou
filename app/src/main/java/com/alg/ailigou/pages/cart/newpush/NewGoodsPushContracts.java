package com.alg.ailigou.pages.cart.newpush;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/1
 * 此类或接口用于  新品推送的view和presenter连接
 */

public interface NewGoodsPushContracts  {



    interface View extends BaseView{
        void update(PageModel<CommodityModel> data);
        //更新ui的方法

    }
    interface Presenter extends BasePresenter<View>{
        //加载数据的方法
        void loadNewGoodsPushData();
    }


}
