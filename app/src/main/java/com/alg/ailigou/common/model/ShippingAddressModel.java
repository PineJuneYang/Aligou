package com.alg.ailigou.common.model;

import java.io.Serializable;

/**
 * Created by 海航
 * on 2017/8/1.
 * 此类或接口用于  收货地址
 */

public class ShippingAddressModel implements Serializable {
    public long id;
    public String telNumber;//电话号码
    public String address;//家庭地址
    public CityModel province;//所在的省
    public CityModel city;  //所在的市
    public CityModel district ;//所在的区
    public String name;//姓名
    public boolean isDefault;//是否是默认地址

    public double lng;//经度
    public double lat;//纬度
}
