package com.alg.ailigou.mock;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.JsonUtils;

/**
 * AiligouApp
 * com.alg.ailigou.mock
 * Created by Chris Chen on 2017/8/28 15:03.
 * Explain:
 */

public class MainTest {
    public static void main(String[] args){

        testApi();
    }

    private static void testApi() {
        NetCallback<HomeDataModel> callback=new NetCallback<HomeDataModel>() {
            @Override
            protected void onComplete(NetResponse<HomeDataModel> netResponse) {
                System.out.println(JsonUtils.objToJson(netResponse.data));
            }
        };
        HomeApi.getHomeData(callback);
    }
}
