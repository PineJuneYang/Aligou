package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.BusinessHolder;

import java.util.List;


/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/24 14:42.
 * Explain:联盟商家
 */

public class BusinessAdapter extends BaseRecyclerAdapter<List<UnionModel>,UnionModel,BusinessHolder> {
    public BusinessAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_business;
    }

    @Override
    protected BusinessHolder createViewHolder(View itemView) {
        return new BusinessHolder(itemView);
    }

    @Override
    protected void refreshView(BusinessHolder holder, int position) {
        UnionModel item = getItem(position);
        ImageLoadUtils.load(mContext,item.imgUrl,holder.image);
        holder.title.setText(item.storeName);
        holder.contacts.setText("联系人:"+item.person);
        holder.phone.setText("TEL:"+item.telNumber);
        holder.address.setText("商店地址："+item.address);
    }
}
