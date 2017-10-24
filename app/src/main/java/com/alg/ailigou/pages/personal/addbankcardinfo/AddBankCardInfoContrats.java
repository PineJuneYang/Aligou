package com.alg.ailigou.pages.personal.addbankcardinfo;

import com.alg.ailigou.common.model.BankModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class AddBankCardInfoContrats {
    interface View extends BaseView {
        /**
         * 展示的时候加载数据成功后填充数据
         */
        void setViewData(BankModel model);

        /**
         * @param type type==0表示是添加的时候 提交的,type=1 表示是修改的时候提交的
         *             修改或者添加成功后的操作
         */
        void commitFinish(int type);


        void showDeleteBankDialog();

        void dismissDeleteBankDialog();

        void toAddCardActivity();
    }

    interface Presenter extends BasePresenter<View> {
        void loadBankData();

        /**
         *  添加或者修改银行卡
         * @param model
         * @param type  type==0表示是添加的时候 提交的,type=1 表示是修改的时候提交的
         */
        void commitBankInfo(BankModel model, int type);

        /**
         * 删除银行卡
         */
        void removeBankCard();

    }
}
