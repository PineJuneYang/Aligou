package com.alg.ailigou.common.model;

import java.io.Serializable;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/7 16:13.
 * Explain:商品分类
 */

public class CommodityTypeModel implements Serializable {
    public int id;//类别id
    public String title;//类别标题
    public String imageUrl;//类别图片
    public String copy;//类别文案

    public CommodityTypeModel() {

    }

    public CommodityTypeModel(int id, String title, String imageUrl, String copy) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.copy = copy;
    }
}
