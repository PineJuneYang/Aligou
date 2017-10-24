package com.alg.ailigou.pages.home.self.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class SelfHeaderHolder extends MyBaseViewHolder {
    @BindView(R.id.bannerview)
    BannerViewPager mBannerview;
    @BindView(R.id.iv_self_head_title_1)
    ImageView mIvSelfHeadTitle1;
    @BindView(R.id.tv_self_head_title_1)
    TextView mTvSelfHeadTitle1;
    @BindView(R.id.ll_self_head_title_1)
    LinearLayout mLlSelfHeadTitle1;
    @BindView(R.id.iv_self_head_title_2)
    ImageView mIvSelfHeadTitle2;
    @BindView(R.id.tv_self_head_title_2)
    TextView mTvSelfHeadTitle2;
    @BindView(R.id.ll_self_head_title_2)
    LinearLayout mLlSelfHeadTitle2;
    @BindView(R.id.iv_self_head_title_3)
    ImageView mIvSelfHeadTitle3;
    @BindView(R.id.tv_self_head_title_3)
    TextView mTvSelfHeadTitle3;
    @BindView(R.id.ll_self_head_title_3)
    LinearLayout mLlSelfHeadTitle3;
    @BindView(R.id.iv_self_head_title_4)
    ImageView mIvSelfHeadTitle4;
    @BindView(R.id.tv_self_head_title_4)
    TextView mTvSelfHeadTitle4;
    @BindView(R.id.ll_self_head_title_4)
    LinearLayout mLlSelfHeadTitle4;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    OnClickListener onClickListener;
    public SelfHeaderHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
    public SelfHeaderHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener) {
        super(itemView, listener);
        this.onClickListener=onClickListener;
    }
    @OnClick({R.id.ll_self_head_title_1, R.id.ll_self_head_title_2, R.id.ll_self_head_title_3, R.id.ll_self_head_title_4})
    public void onViewClicked(View view) {
        if(onClickListener!=null){
            onClickListener.setOnClickListener(view,getAdapterPosition());
        }

    }
}
