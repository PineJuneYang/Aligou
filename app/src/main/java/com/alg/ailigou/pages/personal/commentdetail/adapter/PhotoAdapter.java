package com.alg.ailigou.pages.personal.commentdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.commentdetail.holder.PhotoHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/10
 * 此类或接口用于
 */

public class PhotoAdapter extends BaseHeadAndFootAdapter {



    private List<String> photoUris = new ArrayList<>();
    private LayoutInflater inflater;


    public PhotoAdapter(List datas, Context context) {
        super(datas, context);
        this.photoUris = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View phototView = inflater.inflate(R.layout.alg_item_personal_detail_photo, parent, false);
        return new PhotoHolder(phototView, listener, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PhotoHolder) {

            PhotoHolder photoHolder = (PhotoHolder) holder;
            photoHolder.setData(photoUris.get(position));
        }
    }


}
