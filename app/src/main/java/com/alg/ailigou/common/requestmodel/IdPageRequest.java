package com.alg.ailigou.common.requestmodel;

/**
 * AiligouApp
 * com.alg.ailigou.common.requestmodel
 * Created by Chris Chen on 2017/8/4 13:45.
 * Explain:具有id和分页需求的请求
 */

public class IdPageRequest {
    public long id;//商品id、商家id等需要id的实体类id
    public int page;//分页
    public int pageSize;//页面大小
}
