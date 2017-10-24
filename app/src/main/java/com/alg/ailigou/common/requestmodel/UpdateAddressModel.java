package com.alg.ailigou.common.requestmodel;

import com.alg.ailigou.common.model.ShippingAddressModel;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于 编辑或者新增地址的请求model
 */

public class UpdateAddressModel {
    public int type;//编辑还是新增地址的标记 0,是新增,1是比编辑
    public ShippingAddressModel shippingAddressModel;
}