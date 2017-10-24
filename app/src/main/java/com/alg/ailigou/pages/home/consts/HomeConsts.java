package com.alg.ailigou.pages.home.consts;

import com.alg.ailigou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.consts
 * Created by Chris Chen on 2017/7/6 12:02.
 * Explain:首页需要用到的常量
 */

public interface HomeConsts {
    //功能区标题
    String[] HOME_FUNCTION_TITLES = {"自营", "利购券", "新品", "佳酿", "联盟商家"};
    //功能区图标
    int[] HOME_FENCTION_ICONS = {
            R.mipmap.alg_home_icon_function_self,
            R.mipmap.alg_home_icon_function_ligou_paper,
            R.mipmap.alg_home_icon_function_new_commodity,
            R.mipmap.alg_home_icon_function_wine,
            R.mipmap.alg_home_icon_function_union_business
    };
    //商城分类标题
    String[] HOME_MARKING_CLASSIC_TITLES = {"限时特卖", "本周上新", "大品牌", "超值特惠"};
    //商城分类图标
    int[] HOME_MARKING_CLASSIC_ICONS = {
            R.mipmap.alg_home_icon_function_mall,
            R.mipmap.alg_home_icon_function_ligou_paper,
            R.mipmap.alg_home_icon_function_new_commodity,
            R.mipmap.alg_home_icon_function_wine
    };
    //消息弹出窗口加载的页面路径
    String HOME_NOTICE_URL = "http://192.168.1.112:8020/Project/try/index2.html";

    //热销榜的tablayout名称
    String[] HOME_HOT_SALE_LIST_TAB_TITLE = {"热销推荐榜", "时尚服装榜", "品质美酒榜", "数码家电榜"};

    //本周热卖的tablayout
    String[] HOME_HOT_SALE_WEEK = {"母婴用品", "箱包", "护肤美妆", "家居装饰", "数码电器", "营养保健"};

    //搜索商品的详情tabLayoutTitle
    String[] HOME_SEARCH_ACTIVITY={"综合","销量","价格","人气"};

    //历史搜索热词
    String[] HOME_SEARCH_HISTORY_WORDS_STRING ={"苹果","香蕉","相机","笔记本","游戏卡","苹果","香蕉","相机","笔记本","游戏卡","苹果","香蕉","相机","笔记本","游戏卡","苹果","香蕉","橘子","花生","牛奶","橙子","水蜜桃","干果","火锅","毛血旺"};

    List<String> HOME_SEARCH_HISTORY_WORDS = new ArrayList<>();



   //评论标签
    String[] HOME_COMMENT_LABEL = {"全部","图片","服务态度好","发货速度快","质量很好","性价比"};

}
