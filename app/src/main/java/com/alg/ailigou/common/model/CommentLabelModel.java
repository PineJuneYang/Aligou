package com.alg.ailigou.common.model;

import java.io.Serializable;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/8/2 16:50.
 * Explain:商品评论标签
 */

public class CommentLabelModel implements Serializable {
    public int goodsId;
    public int allCount;
    public int imgCount;
    public int levelOne;
    public int levelTwo;
    public int levelThree;
    public int levelFour;
    public int levelFive;
}
