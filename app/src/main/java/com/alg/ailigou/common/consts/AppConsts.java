package com.alg.ailigou.common.consts;

import com.alg.ailigou.R;

/**
 * com.alg.ailigouapp.common.consts
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 15:44
 * Explain:App公用常量和固定的数组
 */

public interface AppConsts {
    boolean IS_DEBUG=true;//开发状态
    boolean IS_MOCK=false;//模拟状态
    boolean IS_MONKEY=false;//monkey测试开关
    String AILIGOU_APP_IMAGE_SERVER_URL="http://192.168.1.252";//monkey测试开关

    String[] PAGE_TAB_TITLES={"爱利购","分类","联盟商家","购物车","个人中心"};//模块标题
    int[] PAGE_TAB_VIEWS={R.layout.alg_main_tab_custom_view_home,
            R.layout.alg_main_tab_custom_view_classification,
            R.layout.alg_main_tab_custom_view_union,
            R.layout.alg_main_tab_custom_view_cart,
            R.layout.alg_main_tab_custom_view_personal};//模块自定义view，对应顺序与上同

}
