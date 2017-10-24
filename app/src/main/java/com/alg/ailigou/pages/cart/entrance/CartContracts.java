package com.alg.ailigou.pages.cart.entrance;

import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.cart.entrance
 * Created by Chris Chen on 2017/7/7 15:18.
 * Explain:购物车MVP契约
 */

public class CartContracts {
    interface View extends BaseView {
        void cartDataNotify(CartDataModel dataModel);

        void recommendListNotify(PageModel<CommodityModel> commodityModelPageModel);




        void  adapterNotif();

        void successDelete(List<Long> ids);

        void failDelete();

        void successMove();

        void failMove();
    }

    interface Presenter extends BasePresenter<View> {
        void loadCartdata();

        void loadRecommendedList(int type, int page, int pageSize);

        /**
         * @param
         */
        void moveGoodsToFav(List<CommodityModel> goods);

        /**
         * 把购物车商品移到收藏夹
         *
         * @param
         */
        void deleteCart(List<CommodityModel> goods);
    }
}
