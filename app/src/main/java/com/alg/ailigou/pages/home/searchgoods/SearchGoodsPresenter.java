package com.alg.ailigou.pages.home.searchgoods;

import android.text.TextUtils;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.SpUtils;
import com.alg.ailigou.pages.home.consts.HomeConsts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/27
 * 此类或接口用于
 */

public class SearchGoodsPresenter implements SearchGoodsContracts.Presenter ,HomeConsts{

    private SearchGoodsContracts.View mView;

    private List<String> historyList = new ArrayList<>();


    @Inject
    public SearchGoodsPresenter(){}


    @Override
    public void bindView(SearchGoodsContracts.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }


    @Override
    public void getHotGoods() {

        NetCallback<List<String>> callback = new NetCallback<List<String>>() {
            @Override
            protected void onComplete(NetResponse<List<String>> netResponse) {
                if (netResponse.isSuccess){
                    mView.loadHotGoods(netResponse.data);
                }
            }
        };

        HomeApi.getSearchHotGoodsList(1 ,"",callback);

    }

    @Override
    public void getHistoryGoods() {

        String historyWords = SpUtils.read("HISTORY_WORDS", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(historyWords)){

            historyList =  gson.fromJson(historyWords, new TypeToken<List<String>>(){}.getType());
        }
        if (TextUtils.isEmpty(historyWords)||historyWords.equals("")){
            historyList.clear();
        }
           mView.updateHistoryWords(historyList);
//           historyList.clear();

    }


}
