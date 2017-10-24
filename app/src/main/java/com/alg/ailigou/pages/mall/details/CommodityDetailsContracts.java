package com.alg.ailigou.pages.mall.details;

import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.details
 * Created by Chris Chen on 2017/7/12 17:55.
 * Explain:商品详情页契约
 */

public interface CommodityDetailsContracts {
    interface View extends BaseView {
        void updateGoodsDetailsData(CommodityModel goodsModel);//获取商品详情

        void updateRecommetPage(PageModel<CommodityModel> goodsPage);//更新推荐商品分页

        void updateSpecsData(List<String> property);//更新商品规格属性


        void updateCommentLabelData(CommentDataModel data); //更新评论标签

        void successCollection(); //成功添加到收藏
    }

    interface Presenter extends BasePresenter<View> {
        void loadGoodsDetailsData(long goodsId);//获取商品详情

        void loadRecommetPage();//加载推荐商品分页

        void loadSpecsData(long id);//加载商品规格属性

        void addGoodsToFav(long ids);//添加商品到收藏夹

        void addGoodsToCart(CommodityModel model);//添加商品到购物车

        void loadCommentData(long goodsId);//加载商品评论
    }
}
