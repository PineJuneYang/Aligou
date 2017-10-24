package com.alg.ailigou.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.model
 * Created by Chris Chen on 2017/7/6 15:56.
 * Explain:商品
 */

public class CommodityModel implements Serializable{
    //基本信息
    public long id;//商品id
    public String title;//商品标题
    public String imageUrl;//展示图url  购物车的图案,稍微小一点
    public String describe;//商品描述
    public String price;//现在价格
    public String oldPrice;//原来价格
    //end基本信息

    public String imageUrl2;//展示图url  猜你喜欢的图案,比上面大一点
    public String imageUrlBig;//展示大图url  最大的图案
    public int cartCount;//数量  单个商品在购物车中的数量
    public int buyCount;//    单个商品购买的数量
    public boolean isSelect;//购物车是否被选中
    public int cheapTicketCount;//利购券数
    public String brand;//品牌
    public String series;//系列
    public String copy;//文案
    public long countDown;//限时抢购的时候倒计时
    public long browseTime;//浏览该商品的时间

    //附加:商品详情需要
    public int courier;//快递费
    public int soldCount;//已售出
    public String goodRate;//好评率
    public int commentCount;//评论总数
    public List<CommentLabelModel> commentLabelList;//评论标签统计
    public List<CommentModel> commentList;//评论3条
    //立即购买和加入购物车需要
    public List<GoodsSpecsModel> goodsSpecsList;//商品规格属性列表
    public int stock;//对应库存


}
