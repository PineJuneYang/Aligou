package com.alg.ailigou.common.model;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于  线下订单结构
 */

public class OrderOffLineDataModel {
    public String order;//订单号
    public long time;//订单时间
    public List<CommodityModel> goods;
    public int orderState;//订单状态
    public int payType;//支付方式
    public String unionAccount;//商家账号
    public String unionName;//商家名称
    public String unionPhone;//商家电话
}
