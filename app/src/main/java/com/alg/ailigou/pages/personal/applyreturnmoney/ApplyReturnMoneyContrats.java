package com.alg.ailigou.pages.personal.applyreturnmoney;

import android.content.Context;

import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ApplyReturnMoneyContrats {
    interface View extends BaseView {
        /**
         * 选择图片的dialog
         */
        void showPicDiaog();

        void dismissPicDiaog();

        /**
         * 退款原因的dialog
         */
        void showReasonDialog();

        void dismissReasonDialog();

        /**
         * 去退款详情界面
         */
        void toRefundDetails(ReturnGoodsData data);

        /**
         * 上传凭证图片刷新展示
         */
        void imgAdapterNotify();
    }

    interface Presenter extends BasePresenter<View> {
        void submitData(ReturnGoodsData data);

        /**
         * 压缩图片
         * @param imgs
         * @param file
         */
        void compresPic(Context context,List<String> imgs, File file);
    }
}
