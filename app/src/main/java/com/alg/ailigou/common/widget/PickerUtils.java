package com.alg.ailigou.common.widget;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于  时间选择器
 */

public class PickerUtils {

    public static TimePickerView getTimePicker(Context context, TimePickerView.OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        //正确设置方式 原因：注意事项有说明
        startDate.set(1980, 0, 1);
        endDate.set(2050, 12, 31);
        TimePickerView pvTime = new TimePickerView.Builder(context, listener)
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        return pvTime;
    }

    public static OptionsPickerView getCommonPickerView(Context context, String title, List datas, OptionsPickerView.OnOptionsSelectListener listener) {
        return getCommonPickerView(context, title, datas, listener, true);
    }

    public static OptionsPickerView getCommonPickerView(Context context, String title, List datas, OptionsPickerView.OnOptionsSelectListener listener, boolean isCyclic) {
        OptionsPickerView mPickerView = new OptionsPickerView.Builder(context, listener)
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(0x66000000)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCyclic(isCyclic, isCyclic, isCyclic)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        mPickerView.setPicker(datas);
        return mPickerView;
    }
}
