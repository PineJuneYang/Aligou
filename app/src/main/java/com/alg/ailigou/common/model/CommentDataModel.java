package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/8/4 13:33.
 * Explain:商品全部评论页面数据
 */

public class CommentDataModel {
    public int people;//参与人数
    public int goodRate;//好评率
    public CommentLabelModel commentLabelList;//评论标签
    public PageModel<CommentModel> commentPage;//评论分页
}
