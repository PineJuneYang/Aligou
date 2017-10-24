package com.alg.ailigou.common.requestmodel;

/**
 * Created by 玖泞
 * on 2017/7/27
 * 此类或接口用于热门搜索热词的model 和搜索热词的model
 */

public class SearchGoodsRequest {
    public int type ;   //1 表示热门商品 , 2,表示 商家搜索  3, 表示...
    public String keyWords ; //搜索的关键字
}
