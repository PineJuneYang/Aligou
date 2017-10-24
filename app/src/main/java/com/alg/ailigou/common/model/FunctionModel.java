package com.alg.ailigou.common.model;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.model
 * Created by Chris Chen on 2017/7/6 12:09.
 * Explain:功能入口
 */

public class FunctionModel {
    public int id;
    public String title;
    public String icon;
    public int iconRes;
    public int type;
    public int url;

    public FunctionModel(int id, String title, int iconRes) {
        this.id = id;
        this.title = title;
        this.iconRes = iconRes;
    }
}
