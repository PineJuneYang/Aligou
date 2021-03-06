package com.alg.ailigou.common.model;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于 物流信息
 */

public class LogisticsTestModel {
    /**
     * EBusinessID : 1109259
     * OrderCode :
     * ShipperCode : SF  (顺丰)
     * LogisticCode : 118461988807
     * Success : true
     * State : 3
     * Reason : null
     * traces : [{"AcceptTime":"2014/06/25 08:05:37","AcceptStation":"正在派件..(派件人:邓裕富,电话:18718866310)[深圳 市]","Remark":null},{"AcceptTime":"2014/06/25 04:01:28","AcceptStation":"快件在 深圳集散中心 ,准备送往下一站 深圳 [深圳市]","Remark":null},{"AcceptTime":"2014/06/25 01:41:06","AcceptStation":"快件在 深圳集散中心 [深圳市]","Remark":null},{"AcceptTime":"2014/06/24 20:18:58","AcceptStation":"已收件[深圳市]","Remark":null},{"AcceptTime":"2014/06/24 20:55:28","AcceptStation":"快件在 深圳 ,准备送往下一站 深圳集散中心 [深圳市]","Remark":null},{"AcceptTime":"2014/06/25 10:23:03","AcceptStation":"派件已签收[深圳市]","Remark":null},{"AcceptTime":"2014/06/25 10:23:03","AcceptStation":"签收人是：已签收[深圳市]","Remark":null}]
     */

    public String EBusinessID;
    public String OrderCode;
    public String ShipperCode;
    public String LogisticCode;
    public boolean Success;
    public int State;
    public Object Reason;
    public List<TracesBean> traces;

    public static class TracesBean {
        /**
         * AcceptTime : 2014/06/25 08:05:37
         * AcceptStation : 正在派件..(派件人:邓裕富,电话:18718866310)[深圳 市]
         * Remark : null
         */
        public String AcceptTime;
        public String AcceptStation;
        public Object Remark;

    }
}
