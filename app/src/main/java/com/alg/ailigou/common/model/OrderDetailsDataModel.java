package com.alg.ailigou.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 海航
 * on 2017/8/1.
 * 此类或接口用于 订单详情
 */

public class OrderDetailsDataModel implements Serializable {
    public ShippingAddressModel mAddressModel;//收货地址
    public List<CommodityModel> goods;//购买的商品列表
    public String expressFee;//快递费
    public String ligouForMoney;//利购券
    public String allPrice;//总价
    public String orderNumber;//订单编号
    public long orderId;//订单id
    public int orderState;//订单状态  1:待付款 2:待收货 3:待发货 4:待评价,5,已完成,6,退货
    public long orderTime; //订单日期
    public int orderType;//0:表示正常的通过付款卖的商品  1:表示直接通过利购券兑换的商品
    public LogisticsModel logistics;//物流信息
}
