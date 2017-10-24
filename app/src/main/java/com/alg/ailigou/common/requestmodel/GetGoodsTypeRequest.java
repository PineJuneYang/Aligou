package com.alg.ailigou.common.requestmodel;

/**
 * Created by 海航
 * on 2017/8/30.
 * 此类或接口用于
 */

public class GetGoodsTypeRequest {
    public long type;//类型
    public int page;//分页
    public int pageSize;//页面大小
    public int recommendType;//用于区分请求 新品专场 和  好货榜单   1表示新品专场  2表示好货榜单
}
