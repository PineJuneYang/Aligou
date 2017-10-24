package com.alg.ailigou.pages.personal.refundsoraftersale.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.refundsoraftersale.holder.RefundsAfterSaleRecycleHolder;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/18
 * 此类或接口用于
 */

public class RefundsAfterSaleRecycleAdapter extends BaseHeadAndFootAdapter  {



    private LayoutInflater inflater;


    private List<ReturnGoodsData> datas = new ArrayList<>();
    private RefundsAfterSaleAdapter adapter;


    public RefundsAfterSaleRecycleAdapter(List datas, Context context) {
        super(datas, context);
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_personal_refunds_aftesale_recycle, parent, false);
        return new RefundsAfterSaleRecycleHolder(view, null,getOnClickListener(),mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RefundsAfterSaleRecycleHolder) {
            RefundsAfterSaleRecycleHolder  refundsAfterSaleRecycleHolder = (RefundsAfterSaleRecycleHolder) holder;
//            if (adapter == null) {

                //recycleview 设置adapter

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                refundsAfterSaleRecycleHolder.getRecylerView().setLayoutManager(linearLayoutManager);

                adapter = new RefundsAfterSaleAdapter(datas.get(position).goods, mContext);
                refundsAfterSaleRecycleHolder.getRecylerView().setAdapter(adapter);


                adapter.setListener(new OnItemClickListener() {
                    @Override
                    public void setOnItemClickListener(View view, int position) {
                        ToastUtils.showToast(mContext,"跳转到订单详情页");
                    }
                });



//            } else {
//                adapter.notifyDataSetChanged();
//            }



            refundsAfterSaleRecycleHolder.setData(datas.get(position));


        }
    }



}
