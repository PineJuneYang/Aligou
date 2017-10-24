package com.alg.ailigou.common.model;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by 海航
 * on 2017/7/29.
 * 此类或接口用于 地区的model
 */

public class CityModel implements IPickerViewData,Serializable {
    public int parentId;
    public int areaId;
    public String areaName;

    @Override
    public String getPickerViewText() {
        return areaName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CityModel) {
            CityModel model = (CityModel) obj;
            if (model.parentId == parentId && model.areaId == areaId && model.areaName.equals(areaName)) {
                return true;
            }
        }
        return super.equals(obj);
    }
}
