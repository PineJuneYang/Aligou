package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alg.ailigou.R;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/18 13:22.
 * Explain:分类页面左侧分类列表
 */

public class ClassicLayout extends ScrollView implements View.OnClickListener {
    private String[] typeTitles;
    private TextView[] mTextViews;
    private View[] mViews;
    private LinearLayout llContainer;
    private OnItemSelectedChangeListener onItemSelectedChangeListener;

    public ClassicLayout(Context context) {
        super(context);
        initView();
    }

    public ClassicLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        llContainer = new LinearLayout(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llContainer.setLayoutParams(params);
        llContainer.setOrientation(LinearLayout.VERTICAL);
    }

    public void setTitles(String[] typeTitles) {
        this.typeTitles = typeTitles;

        mTextViews = new TextView[typeTitles.length];
        mViews = new View[typeTitles.length];

        View view;
        for (int i = 0; i < typeTitles.length; i++) {
            view = View.inflate(getContext(), R.layout.alg_item_classification_commoditi_type, null);
            view.setId(i);
            view.setOnClickListener(this);
            TextView title = (TextView) view.findViewById(R.id.tv_commodity_type_title);
            title.setText(typeTitles[i]);
            llContainer.addView(view);
            mTextViews[i] = title;
            mViews[i] = view;
        }
        changeTextColor(0);
    }

    private void changeTextColor(int id) {
        for (int i = 0; i < mTextViews.length; i++) {
            if (i != id) {
//                mTextViews[i].setBackgroundResource(android.R.color.transparent);
                mTextViews[i].setBackgroundResource(R.color.alg_classification_commodity_type_bg_normal);
                mTextViews[i].setTextColor(0xff000000);
            } else {
                mTextViews[i].setBackgroundResource(R.color.alg_classification_commodity_type_bg_selected);
                mTextViews[id].setTextColor(0xffff5d5e);
            }
        }
    }

    /**
     * 调整位置
     *
     * @param index
     */
    private void changeTextLocation(int index) {
        //views[clickPosition].getTop()针对其父视图的顶部相对位置
        int x = (mViews[index].getTop() - getHeight() / 2);
        smoothScrollTo(0, x);
    }

    @Override
    public void onClick(View v) {
        changeTextColor((int) v.getId());
        changeTextLocation((int) v.getId());
        //(todo:test)需要判断创建
        if (onItemSelectedChangeListener != null) {
            for (int i = 0; i < mViews.length; i++) {
                if (v == mViews[i]) {
                    onItemSelectedChangeListener.onItemSelected(i);
                }
            }
        }
//        if (v == mViews[0]) {
//            getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, new HotFragment()).commit();
//        } else {
//            getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, new NormalFragment()).commit();
//        }
    }

    public void setOnItemSelectedChangeListener(OnItemSelectedChangeListener onItemSelectedChangeListener) {
        this.onItemSelectedChangeListener = onItemSelectedChangeListener;
    }

    public interface OnItemSelectedChangeListener {
        void onItemSelected(int position);
    }
}
