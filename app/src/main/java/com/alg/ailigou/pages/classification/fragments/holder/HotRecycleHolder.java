package com.alg.ailigou.pages.classification.fragments.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/9/7
 * 此类或接口用于
 */

public class HotRecycleHolder extends MyBaseViewHolder {


    @BindView(R.id.iv_hot_image)
    public ImageView image;
    @BindView(R.id.tv_hot_title)
    public TextView title;

    private Context mContext;
    public HotRecycleHolder(View itemView, OnItemClickListener listener , Context context) {
        super(itemView, listener);
        this.mContext = context;
    }


    public void setData(CommodityTypeModel commodityTypeModel){
        ImageLoadUtils.load(mContext, commodityTypeModel.imageUrl, image);
        title.setText(commodityTypeModel.title);

    }
}
