package com.alg.ailigou.pages.personal.commentdetail.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/10
 * 此类或接口用于
 */

public class PhotoHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_item_comment_detail_photo)
    com.bm.library.PhotoView ivItemCommentDetailPhoto;

    @BindView(R.id.iv_item_comment_detail_delete_photo)
    ImageView ivItemCommentDetailDeletePhoto;
    private Context context;
    private OnClickListener onClickListener;


    public PhotoHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, Context context) {
        super(itemView, listener);
        this.context = context;
        this.onClickListener = onClickListener;
        // 把PhotoView当普通的控件把触摸功能关掉
        ivItemCommentDetailPhoto.disenable();
    }

    public void setData(String s) {
        if (s!=null&& !TextUtils.isEmpty(s)){
            if (s.equals("000")){
                Glide.with(context).load(R.drawable.alg_personal_add_image).into(ivItemCommentDetailPhoto);
                ivItemCommentDetailDeletePhoto.setVisibility(View.GONE);
            }else{
                ImageLoadUtils.setRoundedRectangleImage(context,s,10,ivItemCommentDetailPhoto);
                ivItemCommentDetailDeletePhoto.setVisibility(View.VISIBLE);
            }
        }
    }
    @OnClick({R.id.iv_item_comment_detail_delete_photo,R.id.iv_item_comment_detail_photo})
    public void onViewClicked(View view) {
        if (onClickListener!=null){
            onClickListener.setOnClickListener(view,getAdapterPosition());
        }
    }


}
