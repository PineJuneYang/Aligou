package com.alg.ailigou.common.model;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/10 15:50.
 * Explain:新闻资讯
 */

public class NewsModel {
    public int id;//新闻id
    public String title;//新闻标题
    public String describe;//新闻描述
    public String imageUrl01;//新闻图片1  只有一张图的  放在这里
    public String imageUrl02;//新闻图片2
    public String imageUrl03;//新闻图片3
    public String url;//网页链接
    public String author;//新闻来源
    public long time;//发布时间
    public int style;//新闻结构风格 0:表示  一张图 ,然后描述信息等  1:表示三张图  2:表示一个标题下面是一张大图
}
