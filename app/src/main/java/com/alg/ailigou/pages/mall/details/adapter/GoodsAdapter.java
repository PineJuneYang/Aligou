package com.alg.ailigou.pages.mall.details.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.mall.details.holder.GoodsHolder;

import java.util.List;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/6 17:52.
 * Explain:
 */

public class GoodsAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, GoodsHolder> {



    public GoodsAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_mall_goods_details_recomment;
    }

    @Override
    protected GoodsHolder createViewHolder(View itemView) {
        return new GoodsHolder(itemView);
    }

    @Override
    protected void refreshView(GoodsHolder holder, int position) {
        CommodityModel item = getItem(position);
//        ImageUtils.load(mContext, item.imageUrl, holder.image);
        ImageLoadUtils.loadSquareImage(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
        holder.price.setText("￥:"+item.price);

    }
}
