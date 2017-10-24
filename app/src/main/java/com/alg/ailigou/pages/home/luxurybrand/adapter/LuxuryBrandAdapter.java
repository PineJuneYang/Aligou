package com.alg.ailigou.pages.home.luxurybrand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.holder.CommonBigImageHolder;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.LuxuryBrandHeaderModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.luxurybrand.holder.LuxuryBrandHeaderHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于  奢侈品品牌的adapter
 */

public class LuxuryBrandAdapter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> datas;
    private LuxuryBrandHeaderModel mModel;


    private OnClickListener mOnClickListener;

    public LuxuryBrandAdapter(List<CommodityModel> datas, Context context, LuxuryBrandHeaderModel mModel) {
        super(datas, context);
        this.datas = datas;
        this.mModel = mModel;
    }

    public LuxuryBrandAdapter(List datas, Context context) {
        super(datas, context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_big_img, parent, false);
        return new CommonBigImageHolder(view, listener);
    }

    @Override
    protected LuxuryBrandHeaderHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new LuxuryBrandHeaderHolder((View) mHeaderViews.get(0 + BASE_ITEM_TYPE_HEADER), null, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            LuxuryBrandHeaderHolder viewHolder = (LuxuryBrandHeaderHolder) holder;
            setDataToView(viewHolder, position);
        } else {
            if (holder instanceof CommonBigImageHolder){
                CommonBigImageHolder vh = (CommonBigImageHolder) holder;
                Glide.with(mContext).load(datas.get(position - 1).imageUrlBig).into(vh.getIvGoods());
            }

        }
    }

    /**
     * 填充数据
     *
     * @param holder
     * @param position
     */
    private void setDataToView(LuxuryBrandHeaderHolder holder, int position) {

    }

    public void setOnHeaderClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }


}
