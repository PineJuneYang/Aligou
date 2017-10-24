package com.alg.ailigou.common.model;

import java.io.Serializable;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于  在用户中心展示的各种 数量信息
 */

public class UserShowCountModel implements Serializable {
    public int waitPaymentCount;//待付款数量
    public int waitShipCount;//待发货数量
    public int waitReceiptCount;//待收货数量
    public int waitAssessCount;//待评价数量
    public int afterSaleCount;//售后数量
    public int footprintsCount;//足迹数量
    public int collectionCount;//我的收藏数
    public String cheapTicket;//利购券数量
    public int messagesCount;//未查看消息数
    public int rights;//当前赠送券数量
}
