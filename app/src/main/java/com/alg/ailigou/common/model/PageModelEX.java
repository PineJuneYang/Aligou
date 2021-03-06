package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/17 15:59.
 * Explain:网络请求返回分页模型
 */

public class PageModelEX<T> {
    public float page;//当前页码
    public float pageSize;//页面大小
    public float count;//数据总数
    public boolean hasNext;//是否还有下一页
    public List<T> dataList;//数据列表
}
