package com.alg.ailigou.pages.personal.myinfo;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/4
 * 此类或接口用于
 */

public interface MyInfoContracts {
    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{
      void  updataUserInfo(int sex,String imgUrl,String userName);
    }
}
