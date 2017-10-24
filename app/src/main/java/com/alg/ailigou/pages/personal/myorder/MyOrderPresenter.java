package com.alg.ailigou.pages.personal.myorder;

import com.alg.ailigou.pages.personal.consts.PersonalConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public class MyOrderPresenter extends PersonalConsts implements MyOrderContracts.Presenter {
    private MyOrderContracts.View view;

    private List<String> titles = new ArrayList<>();

    @Inject
    public MyOrderPresenter(){

    }

    @Override
    public void bindView(MyOrderContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadTitle() {
        if (PERSONAL_MY_ORDER.length>0){
            for (int i =0;i<PERSONAL_MY_ORDER.length;i++){
                titles.add(PERSONAL_MY_ORDER[i]);
            }

        }
        if (titles.size()>0){
            view.updateTitles(titles);
        }
    }
}
