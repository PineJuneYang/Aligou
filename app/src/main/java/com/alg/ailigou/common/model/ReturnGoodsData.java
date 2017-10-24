package com.alg.ailigou.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于  退货
 */

public class ReturnGoodsData implements Serializable{
    /**
     * 请求退款使用上面的参数  退款详情使用所有的参数
     */
    public long orderId;//订单id
    public long goodsId;//商品id
    public double returnMoney;//退款金额
    public int reasonCode;//退货原因
    public String reasonDesc;//退货说明
    public List<CommodityModel> goods;//退款的商品
    public List<String> imgs;//退款的上传照片
    public int  returnType;//退款类型  1表示仅退款  2:退货退款



    public String returnNumber;//退货编号
    public long applyTime;//申请时间
    public long returnTime;//退款时间
    public long lastReturnGoodsTime;//还有多久退款成功  申请退款的  倒计时 时间
    public int returnGoodsStatus;//退款状态   0退款中,1退款完成
}
