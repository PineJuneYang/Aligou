package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/24 13:02.
 * Explain:首页限时抢购板块数据
 */

public class LimitBlockModel {
    public long intoTime;//入场时间
    public long nowTime;//当前时间
    public List<CommodityModel> goodsList;//商品列表:单条信息包括 id、名称、图片、现价、原价
}
