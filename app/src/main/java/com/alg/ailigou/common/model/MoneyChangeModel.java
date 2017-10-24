package com.alg.ailigou.common.model;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于  资金变动记录
 */

public class MoneyChangeModel {
    public double changeMoney;//金额变化
    public double canUseMoney;//可用金额
    public double freezeMoney;//冻结
    public String remark;//备注(赠送发放)
    public long time;
    public String type;//类型
}
