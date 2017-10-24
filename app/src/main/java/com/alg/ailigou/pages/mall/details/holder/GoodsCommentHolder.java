package com.alg.ailigou.pages.mall.details.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.holder
 * Created by Chris Chen on 2017/7/6 17:50.
 * Explain:商品评论holder
 */

public class GoodsCommentHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_comment_photo)
    public ImageView photo;
    @BindView(R.id.tv_comment_username)
    public TextView username;
    @BindView(R.id.tv_comment_publish_time)
    public TextView publishTime;
    @BindView(R.id.tv_comment_cotent)
    public TextView content;
    @BindView(R.id.tv_comment_standard)
    public TextView standard;
    @BindView(R.id.rv_mall_goods_details_comment_images)
    public CustomRecyclerView images;

    public GoodsCommentHolder(View itemView) {
        super(itemView);
    }
}
