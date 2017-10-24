package com.alg.ailigou.common.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于 联盟商家搜索时候的商品分类
 */

public class UnionTypeModel implements IPickerViewData {
    public String typeName;//分类名称  酒类  服装  其他 等
    public int id;//分类id

    @Override
    public String getPickerViewText() {
        return typeName;
    }
}
