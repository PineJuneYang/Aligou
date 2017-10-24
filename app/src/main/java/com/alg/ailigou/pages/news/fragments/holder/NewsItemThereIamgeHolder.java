package com.alg.ailigou.pages.news.fragments.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/13.
 * 此类或接口用于  新闻条目的包含三个图片的holder
 */

public class NewsItemThereIamgeHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_news_desc)
    TextView mTvNewsDesc;
    @BindView(R.id.image_1)
    ImageView mImage1;
    @BindView(R.id.image_2)
    ImageView mImage2;
    @BindView(R.id.image_3)
    ImageView mImage3;

    public TextView getTvNewsDesc() {
        return mTvNewsDesc;
    }

    public void setTvNewsDesc(TextView tvNewsDesc) {
        mTvNewsDesc = tvNewsDesc;
    }

    public ImageView getImage1() {
        return mImage1;
    }

    public void setImage1(ImageView image1) {
        mImage1 = image1;
    }

    public ImageView getImage2() {
        return mImage2;
    }

    public void setImage2(ImageView image2) {
        mImage2 = image2;
    }

    public ImageView getImage3() {
        return mImage3;
    }

    public void setImage3(ImageView image3) {
        mImage3 = image3;
    }

    public NewsItemThereIamgeHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
