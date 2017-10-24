package com.alg.ailigou.pages.personal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于 判断电话号码的格式
 */

public class PhoneUtil {

    /**
     * 判断电话号码是否符合格式.
     *
     * @param inputText the input text
     * @return true, if is phone
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
}
