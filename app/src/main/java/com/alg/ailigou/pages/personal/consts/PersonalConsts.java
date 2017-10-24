package com.alg.ailigou.pages.personal.consts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 海航
 * on 2017/8/3.
 * 此类或接口用于
 */

public class PersonalConsts {
    public static final int HAS_BANK_NUMBER = 100;//已经有银行卡了
    public static final int HAS_NO_BANK_NUMBER = 101;//该用户尚未添加银行卡
    public static final int ADD_BANK_SUCCESS = 102;//该用户添加成功之后,又再次跳转当前界面,因为界面是一样的

    public static final int TYPE_BANK_ADD_COMMIT = 0;//添加银行卡信息的提交数据
    public static final int TYPE_BANK_EDIT_COMMIT = 1;//修改银行卡信息的数据修改


    public static List<String> getBankList() {
        List<String> banks = new ArrayList<>();
        banks.add("中国农业银行");
        banks.add("中国银行");
        banks.add("中国建设银行");
        banks.add("中国工商银行");
        banks.add("交通银行");
        banks.add("邮政银行");
        banks.add("国家开发银行");
        banks.add("中国农业发展银行");
        banks.add("中国进出口银行");
        banks.add("中国农业银行");
        return banks;
    }

    /**
     * 退款原因
     *
     * @return
     */
    public static List<String> getRefundsReasonList() {
        List<String> reasons = new ArrayList<>();
        reasons.add("不想要了");
        reasons.add("拍错/拍重");
        reasons.add("没货");
        reasons.add("协商退款");
        reasons.add("其他");
        return reasons;
    }


    //我的收藏标题
    public String[] PERSONAL_MY_COLLECTION = {"商品", "商家"};
    //我的收藏标题
    public String[] PERSONAL_MY_ORDER = {"全部", "待付款", "待发货", "待收货", "待评价"};
    /**
     * 这个是我的订单  几个列表 进入详情的 标记
     */
    public static final int ORDER_TYPE_EXCHANGE=1;//利购券兑换的商品
    public static final int ORDER_TYPE_BUY=0;//普通购买的商品
    public static final int FINISH_ORDER = 0;//所流程已经走完的
    public static final int WAIT_PAR_MONEY = 1;//待付款
    public static final int WAIT_RECEIVE_GOODS = 2;//待收货
    public static final int WAIT_SEND_GOODS = 3;//待发货
    public static final int WAIT_COMMENT = 4;//待评价
    /**
     * 个人中心 公用的h5 界面 的标记
     */
    public static final int MEMBER_GRADE = 1;//会员等级
    public static final int JOIN_PLATFORM = 2;//入驻平台
    public static final int USE_EXPLAIN = 3;//使用说明
    public static final int CHANGE_RULE = 4;//兑换规则
    public static final int REFUNDS_DESC = 5;//退款说明

}
