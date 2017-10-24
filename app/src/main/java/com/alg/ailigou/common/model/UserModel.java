package com.alg.ailigou.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * com.alg.ailigouapp.common.model
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 11:16
 * Explain:用户
 */

public class UserModel implements Serializable {
    public int id;
    public String name;
    public int sex;//0保密,1男  2,女
    public int age;
    public String imageUrl;//头像
    public String telNumber;//电话号码
    public String address;//家庭地址
    public  List<ShippingAddressModel> shippingAddressModel;//收货地址
    public  BankModel bank;//银行卡信息
    public UserShowCountModel showCountModel;//在个人中心  展示各种数量
}
