package com.alg.ailigou.common.model;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by 海航
 * on 2017/8/3.
 * 此类或接口用于  用户银行卡model
 */

public class BankModel implements IPickerViewData,Serializable {
    public String name;
    public String bankNumber;//银行卡号
    public String bankBranchAddress;//银行网点
    public String bankName;//银行种类  现在9大行
    public String idNumber;//身份证号

    @Override
    public String getPickerViewText() {
        return bankName;
    }
}
