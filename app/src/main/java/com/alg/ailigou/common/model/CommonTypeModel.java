package com.alg.ailigou.common.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by 海航
 * on 2017/8/30.
 * 此类或接口用于  返回只有一个type 和对应的信息
 */

public class CommonTypeModel implements IPickerViewData {
    public int type;
    public String content;

    @Override
    public String getPickerViewText() {
        return content;
    }
}
