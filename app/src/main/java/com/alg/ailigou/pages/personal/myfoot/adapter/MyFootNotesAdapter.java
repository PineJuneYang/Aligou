package com.alg.ailigou.pages.personal.myfoot.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.myfoot.holder.MyFootNotesHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/8/8.
 * 此类或接口用于  我的足迹还有 收藏列表中的  商品列表
 */

public class MyFootNotesAdapter extends BaseHeadAndFootAdapter {

    @BindView(R.id.tv_title_time)
    TextView mTvTitleTime;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    @BindView(R.id.tv_goods)
    ImageView mTvGoods;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_now_price)
    TextView mTvGoodsNowPrice;
    @BindView(R.id.tv_goods_old_price)
    TextView mTvGoodsOldPrice;
    private List<CommodityModel> datas;
    private boolean isCollection;//是否是我的收藏


    private boolean isEdit;

    private boolean isSelected;
    private boolean isAllNoSelected;


    public void setAllNoSelected(boolean allNoSelected) {
        isAllNoSelected = allNoSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyDataSetChanged();
    }

    public MyFootNotesAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    /**
     * @param datas
     * @param context
     * @param isGoods 是否是我的收藏
     */
    public MyFootNotesAdapter(List datas, Context context, boolean isGoods) {
        super(datas, context);
        this.datas = datas;
        this.isCollection = isGoods;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new MyFootNotesHolder(LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_myfoot, parent, false), listener, getOnClickListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyFootNotesHolder) {
            MyFootNotesHolder vh = (MyFootNotesHolder) holder;
            Glide.with(mContext).load(datas.get(position).imageUrl).into(vh.getIvGoods());
            vh.getTvTitleTime().setText(TimeUtils.format(datas.get(position).browseTime,TimeUtils.PATTERN2));
            vh.getTvGoodsName().setText(datas.get(position).title);
            vh.getTvGoodsNowPrice().setText("￥:" + datas.get(position).price);
            vh.getTvGoodsOldPrice().setText("￥:" + datas.get(position).oldPrice);
            vh.getTvGoodsOldPrice().setPaintFlags(vh.getTvGoodsOldPrice().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if (!isCollection) {
                //不是我的收藏,说明是我的足迹
                if (position == 0) {
                    vh.getTvTitleTime().setVisibility(View.VISIBLE);
                } else {
                    if (TimeUtils.format(datas.get(position - 1).browseTime, TimeUtils.PATTERN2).equals(TimeUtils.format(datas.get(position).browseTime, TimeUtils.PATTERN2))) {
                        vh.getTvTitleTime().setVisibility(View.GONE);
                    } else {
                        vh.getTvTitleTime().setVisibility(View.VISIBLE);
                    }
                }
            }
            /**
             * 通过isEdit 控制左边选择按钮 隐藏 显示
             */
            if (isEdit) {
                vh.getLliSelect().setVisibility(View.VISIBLE);


//                if (isSelected) {//全部选中
//                    if (!datas.get(position).isSelect){
//                        //数据没有选中
//                        vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_all_choose);
//                    }else {
//                        vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
//                    }
//                } else {
//                    if (isAllNoSelected) {
//                        //全部不选中
//                        vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_all_choose);
//                    } else {
//                        /**
//                         * 通过isSelect 控制左边选择按钮 的样式
//                         */
//                        if (datas.get(position).isSelect) {
//                            vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
//                        } else {
//                            vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_all_choose);
//                        }
//                    }
//                }
                if (datas.get(position).isSelect){
                    vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
                }else {
                    vh.getIvSelect().setBackgroundResource(R.drawable.alg_cart_all_choose);
                }


            } else {
                vh.getLliSelect().setVisibility(View.GONE);
            }

        }
    }


}
