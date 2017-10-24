package com.alg.ailigou;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.JsonUtils;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        NetCallback<HomeDataModel> callback=new NetCallback<HomeDataModel>() {
            @Override
            protected void onComplete(NetResponse<HomeDataModel> netResponse) {
                System.out.println(JsonUtils.objToJson(netResponse.data));
            }
        };
        HomeApi.getHomeData(callback);
    }
}