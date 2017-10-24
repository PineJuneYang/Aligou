package com.alg.ailigou.pages.home.ligouchangenotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.ligouchangenotes.holder.LigouChangeNotesHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 利购券兑换记录
 */

public class LigouChangeNotesAdapter extends BaseHeadAndFootAdapter {
    public static int TYPE_NOTES = 1;//利购券记录
    public static int TYPE_OVERAGE = 2;//利购券余额
    public int type;

    public LigouChangeNotesAdapter(List datas, Context context) {
        super(datas, context);
        this.type = TYPE_NOTES;
    }

    public LigouChangeNotesAdapter(List datas, Context context, int type) {
        super(datas, context);
        this.type = type;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_ligou_change, parent, false);
        return new LigouChangeNotesHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LigouChangeNotesHolder) {
            LigouChangeNotesHolder vh = (LigouChangeNotesHolder) holder;
            if (type == TYPE_NOTES) {
                LigouExchangeModel model1 = (LigouExchangeModel) datas.get(position);
                vh.getTv1().setText(model1.exchangeTime);
                vh.getTv2().setVisibility(View.GONE);

                vh.getTv2().setText(model1.orderNumber);
                vh.getTv3().setText(model1.value + "元");
                vh.getTv4().setText(getStateNotesDesc(model1.exchangeState));
            } else if (type == TYPE_OVERAGE) {
                LigouOverageModel model2= (LigouOverageModel) datas.get(position);
                vh.getTv1().setText(model2.obtainTime);
                vh.getTv3().setText(getStateOverageDesc((int) model2.obtainType));
                vh.getTv4().setText(model2.obtainDetails);
            }


        }

    }

    String getStateNotesDesc(int state) {
        String type = "";
        switch (state) {
            case 1:
                type = "未处理";
                break;
            case 2:
                type = "处理中";
                break;
            case 3:
                type = "已处理";
                break;
            default:
                break;
        }
        return type;
    }
    String getStateOverageDesc(int state) {
        String type = "赠送";
        switch (state) {
            case 1:
                type = "赠送";
                break;
            case 2:

                break;
            case 3:

                break;
            default:
                break;
        }
        return type;
    }
}
