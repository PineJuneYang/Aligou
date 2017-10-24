package com.alg.ailigou.mock.personal;

import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.LigouOverageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 海航
 * on 2017/8/17.
 * 此类或接口用于
 */

public class PersonMockData {
    public static List<LigouOverageModel> getLigouOverageModels() {
        List<LigouOverageModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LigouOverageModel model = new LigouOverageModel();
            model.count = i + 100+"";
            model.obtainTime = "1999年12月";
            model.obtainType = 0;
            model.obtainDetails = "123";
            list.add(model);
        }
        return list;
    }

    public static List<LigouExchangeModel> getLigouExchangeModels() {
        List<LigouExchangeModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LigouExchangeModel model = new LigouExchangeModel();
            model.orderNumber = i + 100 + "";
            model.exchangeState = 0;
           model.exchangeTime = "1999年12月";
            model.value = i + 100+"";
            list.add(model);
        }
        return list;
    }
}
