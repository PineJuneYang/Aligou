package com.alg.ailigou.pages.home.hotrecommend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.hotrecommend.holder.HotRecommendHeaderHolder;
import com.alg.ailigou.pages.home.hotrecommend.holder.HotRecommendHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于
 */

public class HotRecommendAdapter extends BaseHeadAndFootAdapter {
    List<CommodityModel> datas;

    HotRecommendDataModel dataModel;


    private ItemLiftClickListener mLiftClickListener;
    private ItemRightClickListener mRightClickListener;

    /**
     * 拿到
     *
     * @param dataModel
     */
    public void setDataModel(HotRecommendDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public HotRecommendAdapter(List<CommodityModel> datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected int getRealItemCount() {
        return datas == null ? 0 : datas.size() / 2;
    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_hot_recommend, parent, false);
        return new HotRecommendHolder(view, null);
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new HotRecommendHeaderHolder((View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER + 0), null, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER) {
            HotRecommendHeaderHolder headerHolder = (HotRecommendHeaderHolder) holder;
            if (dataModel != null) {
                headerHolder.setData(dataModel.cheapGoods);
            }
        } else {
            if (holder instanceof HotRecommendHolder) {
                HotRecommendHolder viewHolder = (HotRecommendHolder) holder;
                if (position == 1) {
                    viewHolder.getLlHomeRecommendCreazyBg().setBackgroundResource(R.drawable.alg_home_crazyshoppingforexplosion);
                    viewHolder.getLlLeftItem().setPadding(MeasureUtils.dp2px(16),MeasureUtils.dp2px(70),MeasureUtils.dp2px(6),MeasureUtils.dp2px(0));
                    viewHolder.getLlRightItem().setPadding(MeasureUtils.dp2px(6),MeasureUtils.dp2px(70),MeasureUtils.dp2px(16),MeasureUtils.dp2px(0));
                } else {
                    viewHolder.getLlHomeRecommendCreazyBg().setBackgroundResource(R.drawable.alg_home_recommend_product2);
                    viewHolder.getLlLeftItem().setPadding(MeasureUtils.dp2px(16),MeasureUtils.dp2px(40),MeasureUtils.dp2px(6),MeasureUtils.dp2px(0));
                    viewHolder.getLlRightItem().setPadding(MeasureUtils.dp2px(6),MeasureUtils.dp2px(40),MeasureUtils.dp2px(16),MeasureUtils.dp2px(0));
                }
                viewHolder.getLlLeftItem().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getLiftClickListener() != null) {
                            getLiftClickListener().setOnItemLiftClickListener(v, position);
                        }
                    }
                });
                viewHolder.getLlRightItem().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getRightClickListener() != null) {
                            getRightClickListener().setOnItemRightClickListener(v, position);
                        }
                    }
                });
                CommodityModel model1 = datas.get((position - 1) * 2);
                //todo 原始的model1.imageUrl 没数据,方便调UI,暂时用了一个有数据的url
                Glide.with(mContext).load("http://192.168.1.107/upload/images/bag.jpg").into(viewHolder.getIvGoods1());
                //后面好改
//                ImageLoadUtils.load(mContext,model1.imageUrl,viewHolder.getIvGoods1());
                viewHolder.getTvGoodsPrice1().setText("￥:" + model1.price);
                viewHolder.getTvGoodsTitle1().setText(model1.title);

                CommodityModel model2 = datas.get((position - 1) * 2 + 1);
                //todo 原始的model1.imageUrl 没数据,方便调UI,暂时用了一个有数据的url
                Glide.with(mContext).load("http://192.168.1.107/upload/images/bag.jpg").into(viewHolder.getIvGoods2());
//                ImageLoadUtils.load(mContext,model2.imageUrl,viewHolder.getIvGoods2());
                viewHolder.getTvGoodsPrice2().setText("￥:" + model2.price);
                viewHolder.getTvGoodsTitle2().setText(model2.title);

            }
        }

    }


    public ItemLiftClickListener getLiftClickListener() {
        return mLiftClickListener;
    }

    public void setLiftClickListener(ItemLiftClickListener liftClickListener) {
        mLiftClickListener = liftClickListener;
    }

    public ItemRightClickListener getRightClickListener() {
        return mRightClickListener;
    }

    public void setRightClickListener(ItemRightClickListener rightClickListener) {
        mRightClickListener = rightClickListener;
    }


    public interface ItemLiftClickListener {
        void setOnItemLiftClickListener(View view, int potison);
    }

    public interface ItemRightClickListener {
        void setOnItemRightClickListener(View view, int potison);
    }

}
