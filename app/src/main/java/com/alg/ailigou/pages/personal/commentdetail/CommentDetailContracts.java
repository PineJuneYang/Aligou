package com.alg.ailigou.pages.personal.commentdetail;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by 玖泞
 * on 2017/8/10
 * 此类或接口用于
 */

public interface CommentDetailContracts {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 提交图片
         */
        void submitImgs(List<File> files);

        /**
         * 提交评价
         * * public List<String> imgs;  //评论的图片
         * public String content;//内容
         * public long orderId;//订单id
         * public long goodsId;//评价的商品
         * public int level;//评论等级(1-5)
         * public int isHideName;//评论等级(1-5)
         *
         * map中需包含这几个字段
         */
        void submitData( Map<String, Object> map);
    }
}
