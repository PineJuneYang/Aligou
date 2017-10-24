package com.alg.ailigou.common.requestmodel;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于 评价的发布评价的model
 */

public class CommentRequest {
    public List<String> imgs;  //评论的图片
    public String content;//内容

    public long orderId;//订单id
    public long goodsId;//评价的商品
    public int level;//评论等级(1-5)
    public int isHideName;// 是否显示名字  0 显示  1隐藏

}
