package com.alg.ailigou.pages.home.self.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.widget.AutoImageView;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class SelfHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_self_classic_image)
    AutoImageView ivSelfClassicImage;
    @BindView(R.id.tv_self_classic_price)
    TextView tvSelfClassicPrice;
    @BindView(R.id.tv_self_classic_title)
    public TextView title;

    public AutoImageView getIvSelfClassicImage() {
        return ivSelfClassicImage;
    }

    public void setIvSelfClassicImage(AutoImageView ivSelfClassicImage) {
        this.ivSelfClassicImage = ivSelfClassicImage;
    }

    public TextView getTvSelfClassicPrice() {
        return tvSelfClassicPrice;
    }

    public void setTvSelfClassicPrice(TextView tvSelfClassicPrice) {
        this.tvSelfClassicPrice = tvSelfClassicPrice;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    OnClickListener onClickListener;

    public SelfHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

}
