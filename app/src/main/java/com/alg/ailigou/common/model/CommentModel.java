package com.alg.ailigou.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/8/2 17:23.
 * Explain:商品评论
 */

public class CommentModel implements Serializable {
    public int id;
    public String name;//评论者
    public String photo;//评论者头像url
    public String content;//评论内容
    public String standard;//规格标准信息
    public long time;//发表时间
    public List<String> images;//买家晒图
}
