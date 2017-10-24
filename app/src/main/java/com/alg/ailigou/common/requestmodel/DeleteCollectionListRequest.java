package com.alg.ailigou.common.requestmodel;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/17.
 * 此类或接口用于  删除我的收藏的请求类
 */

public class DeleteCollectionListRequest {
    List<Integer>  ids;
    int tepe;//  type:0 商品     1:  商家    2:新闻资讯
}
