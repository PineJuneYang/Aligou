package com.alg.ailigou.pages.cart.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/11.
 * 此类或接口用于
 */

public abstract class BaseVLayoutAdapter<T> extends DelegateAdapter.Adapter<MyBaseViewHolder> {
    public static final int BASE_ITEM_TYPE_HEADER_BANNER = 0x111;//广告栏
    public static final int BASE_ITEM_TYPE_HEADER_TITLE = 0x222;//标题栏
    public static final int BASE_ITEM_TYPE_CRAT = 0x333;//购物车条目
    public static final int BASE_ITEM_TYPE_RECOMMEND = 0x444;//推荐商品
    public static final int BASE_ITEM_TYPE_HORIZONTAL = 0x555;//横行的一列
    public static final int BASE_ITEM_TYPE_BANNER_WIDTH_BG = 0x666;//带有背景的banner
    protected List<T> datas;
    protected Context mContext;
    protected LayoutHelper mHelper;
    protected int type;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    protected OnItemClickListener listener;


    public BaseVLayoutAdapter(List<T> datas, Context context, LayoutHelper helper) {
        this.datas = datas;
        mContext = context;
        mHelper = helper;
    }

    public BaseVLayoutAdapter(List<T> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    public BaseVLayoutAdapter(List<T> datas, Context context, LayoutHelper helper, int type) {
        this.datas = datas;
        mContext = context;
        mHelper = helper;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper == null ? new LinearLayoutHelper() : mHelper;
    }

    @Override
    public int getItemCount() {
        if (datas==null||datas.size()==0){
            return 0;
        }
        if (type == BASE_ITEM_TYPE_RECOMMEND){
            return datas.size();
        }
        if (type == BASE_ITEM_TYPE_HEADER_BANNER || type == BASE_ITEM_TYPE_HEADER_TITLE || type == BASE_ITEM_TYPE_BANNER_WIDTH_BG || type == BASE_ITEM_TYPE_HORIZONTAL) {
            return 1;
        } else {
            return  datas.size();
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * 点击事件  刚开始我是放在了viewholder里面但是  在那里面  position 是根据不同的adapter 然后数量是累加的不是从0开始的
     * 所以就放在这里 在bindViewHolder带哦用即可,正常的adapter  推荐在 viewholder里面写.不然有可能在使用第三方框架出现问题
     *
     * @param viewHolder
     * @param view
     * @param position
     */
    protected void setOnItemClickListener(MyBaseViewHolder viewHolder, final View view, final int position) {
        if (viewHolder.getItemView() == null) {
            return;
        }
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.setOnItemClickListener(view, position);
                }
            }
        });
    }

}
