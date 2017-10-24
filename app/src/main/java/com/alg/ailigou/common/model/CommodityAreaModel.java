package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/7 13:04.
 * Explain:商品专区1
 */

public class CommodityAreaModel {
    public String title;//专区1标题
    public List<HomeCommodityTypeModel> commodityTypeList;//专区1类别列表，包含三个类别对象,依次为：限时抢购、天天特价和本周热卖

}
