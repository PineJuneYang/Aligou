package com.alg.ailigou.common.requestmodel;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于
 */

public class PageWithTimeRequest {
    public int page;//请求第几页
    public int pageSize;//每页请求多少
    public long startTime;
    public long endTime;
    public int type;//处理状态
}
