package com.alg.ailigou.pages.personal.mycollection;

import com.alg.ailigou.pages.personal.consts.PersonalConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于
 */

public class MyCollectionPresenter extends PersonalConsts implements MyCollectionContracts.Presenter {


    @Inject
    public MyCollectionPresenter(){}


    private MyCollectionContracts.View view;

    private List<String> titles = new ArrayList<>();


    @Override
    public void bindView(MyCollectionContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view =null;
    }

    @Override
    public void loadTitle() {
        if (PERSONAL_MY_COLLECTION.length>0){
            for (int i =0;i<PERSONAL_MY_COLLECTION.length;i++){
                titles.add(PERSONAL_MY_COLLECTION[i]);
            }

        }
        if (titles.size()>0){
            view.updateTitles(titles);
        }
    }

}
