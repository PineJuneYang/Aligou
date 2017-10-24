package com.alg.ailigou.common.model;

import java.io.Serializable;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/8/9 16:06.
 * Explain:商品规格
 */

public class GoodsSpecsModel implements Serializable {
    public long id;//规格id
    public String title;//规格属性标题
    public double price;//对应价格
    public double oldPrice;//对应原价格
    public int stock;//对应库存
}
