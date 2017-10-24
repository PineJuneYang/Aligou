package com.alg.ailigou.pages.home.hotsalelist;

import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;
import com.alg.ailigou.pages.home.hotsaleweek.HotSaleWeekContracts;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于
 */

public interface HotSaleListContracts {
    interface View extends BaseView {
        void updateHotSaleData(HomeHotSaleCommodityModel models);//更新活动banner数据
        void updateTablayoutTitle(List<String> tabTitles);



    }
    interface Presenter extends BasePresenter<View> {


//        void loadActionBannerData();//加载活动banner数据
        void loadTabLayoutTitle();


        void loadHotSaleGoodsData(int page,int pageSize);
    }
}
