package com.alg.ailigou.pages.home.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.utils
 * Created by Chris Chen on 2017/7/27 13:10.
 * Explain:首页的工具
 */

public class HomeUtils {
    //把时间戳转换为时分秒
    public static LimitTimeModel getLimitTime(long timeStamp) {
        LimitTimeModel limitTime = new LimitTimeModel();
        limitTime.hour = (int) (timeStamp / 1000 / 60 / 60);
        limitTime.minute = (int) (timeStamp / 1000 / 60 % 60);
        limitTime.second = (int) (timeStamp / 1000 % 60);
        limitTime.milliSecond = (int) (timeStamp % 1000);

        limitTime.hourStr=String.format("%02d",limitTime.hour);
        limitTime.minuteStr=String.format("%02d",limitTime.minute);
        limitTime.secondStr=String.format("%02d",limitTime.second);
        limitTime.milliSecondStr=String.format("%03d",limitTime.milliSecondStr);
        return limitTime;
    }
    /**
     * 判断字符串是否全部由数字组成
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
