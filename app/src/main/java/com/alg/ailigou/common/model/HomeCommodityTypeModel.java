package com.alg.ailigou.common.model;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/11 09:50.
 * Explain:首页商品分类列表
 */

public class HomeCommodityTypeModel {
    public int id;//类别id
    public String title;//类别标题
    public String imageUrl;//类别图片
    public String copy;//类别文案

    public HomeCommodityTypeModel() {
    }

    public HomeCommodityTypeModel(int id, String title, String imageUrl, String copy) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.copy = copy;
    }
}
