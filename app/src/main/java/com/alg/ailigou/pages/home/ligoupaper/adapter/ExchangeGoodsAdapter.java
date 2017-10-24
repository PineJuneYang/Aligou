package com.alg.ailigou.pages.home.ligoupaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.ligoupaper.holder.ExchangeGoodsHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/17.
 * 此类或接口用于 利购券商城的adapter
 */

public class ExchangeGoodsAdapter extends BaseHeadAndFootAdapter {
    List<CommodityModel> datas;
    private OnClickListener mOnClickListener;

    public OnClickListener getClickListener() {
        return mOnClickListener;
    }

    public void setOnOnExchangeClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public ExchangeGoodsAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_exchange_goods, parent, false);
        return new ExchangeGoodsHolder(view, listener);
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        // View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_ligoupaper_head, parent, false);
        return new MyBaseViewHolder((View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER), getListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER) {
            //前面activity已经设置了数据
        } else {
            if (holder instanceof ExchangeGoodsHolder) {
                ExchangeGoodsHolder vh = (ExchangeGoodsHolder) holder;
//            Glide.with(mContext).load(datas.get(position-getHeadersCount())).into(vh.getIvGoods());

                ImageLoadUtils.load(mContext,datas.get(position-getHeadersCount()).imageUrl,vh.getIvGoods());
                vh.getTvGoodsPrice().setText("商品价格:" + datas.get(position - getHeadersCount()).price);

                String str="<font color='#000000'><small>利购券:</small></font><font color='#FF0000'><small> ￥"+datas.get(position - getHeadersCount()).cheapTicketCount+"</small></font>";
//                vh.getTvCheapTicketCount().setText("利购券:" + datas.get(position - getHeadersCount()).cheapTicketCount);

                vh.getTvCheapTicketCount().setText(Html.fromHtml(str));
                vh.getTvCheapTicketCount().setTextSize(MeasureUtils.sp2px(7));
                if (datas.get(position - getHeadersCount()).soldCount == 1){
                    //表示已经售出了

                    vh.getTvExchangeNow().setText("已抢光");
                    vh.getTvExchangeNow().setBackgroundResource(R.drawable.alg_home_ligou_papaer_greybtn);
                }else{
                    vh.getTvExchangeNow().setText("立即兑换");
                    vh.getTvExchangeNow().setBackgroundResource(R.drawable.alg_home_redbtn);
                }



                vh.getTvExchangeNow().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickListener != null) {
                            mOnClickListener.setOnClickListener(v, position);
                        }
                    }
                });
            }
        }

    }


}
