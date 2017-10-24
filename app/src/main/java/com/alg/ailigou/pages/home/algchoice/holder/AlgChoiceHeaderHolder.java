package com.alg.ailigou.pages.home.algchoice.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class AlgChoiceHeaderHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_goods1)
    ImageView mIvGoods1;
    @BindView(R.id.tv_title1)
    TextView mTvTitle1;
    @BindView(R.id.ll_goods1)
    LinearLayout mLlGoods1;
    @BindView(R.id.iv_goods2)
    ImageView mIvGoods2;
    @BindView(R.id.tv_title2)
    TextView mTvTitle2;
    @BindView(R.id.ll_goods2)
    LinearLayout mLlGoods2;
    @BindView(R.id.iv_goods3)
    ImageView mIvGoods3;
    @BindView(R.id.tv_title3)
    TextView mTvTitle3;
    @BindView(R.id.ll_goods3)
    LinearLayout mLlGoods3;
    @BindView(R.id.iv_goods4)
    ImageView mIvGoods4;
    @BindView(R.id.tv_title4)
    TextView mTvTitle4;
    @BindView(R.id.ll_goods4)
    LinearLayout mLlGoods4;
    @BindView(R.id.iv_goods5)
    ImageView mIvGoods5;
    @BindView(R.id.tv_title5)
    TextView mTvTitle5;
    @BindView(R.id.ll_goods5)
    LinearLayout mLlGoods5;
    @BindView(R.id.iv_goods6)
    ImageView mIvGoods6;
    @BindView(R.id.tv_title6)
    TextView mTvTitle6;
    @BindView(R.id.ll_goods6)
    LinearLayout mLlGoods6;
    @BindView(R.id.iv_goods7)
    ImageView mIvGoods7;
    @BindView(R.id.tv_title7)
    TextView mTvTitle7;
    @BindView(R.id.ll_goods7)
    LinearLayout mLlGoods7;
    @BindView(R.id.iv_goods8)
    ImageView mIvGoods8;
    @BindView(R.id.tv_title8)
    TextView mTvTitle8;
    @BindView(R.id.ll_goods8)
    LinearLayout mLlGoods8;
    @BindView(R.id.tv_recommend_title)
    TextView mTvRecommendTitle;
    @BindView(R.id.ll_recommend)
    LinearLayout mLlRecommend;
    @BindView(R.id.tv_cheap_title1)
    TextView mTvCheapTitle1;
    @BindView(R.id.tv_cheap_desc1)
    TextView mTvCheapDesc1;
    @BindView(R.id.iv_choice_goods1)
    ImageView mIvChoiceGoods1;
    @BindView(R.id.ll_choice_1)
    LinearLayout mLlChoice1;
    @BindView(R.id.tv_cheap_title2)
    TextView mTvCheapTitle2;
    @BindView(R.id.tv_cheap_desc2)
    TextView mTvCheapDesc2;
    @BindView(R.id.iv_choice_goods2)
    ImageView mIvChoiceGoods2;
    @BindView(R.id.ll_choice_2)
    LinearLayout mLlChoice2;
    @BindView(R.id.tv_cheap_title3)
    TextView mTvCheapTitle3;
    @BindView(R.id.tv_cheap_desc3)
    TextView mTvCheapDesc3;
    @BindView(R.id.iv_choice_goods3)
    ImageView mIvChoiceGoods3;
    @BindView(R.id.ll_choice_3)
    LinearLayout mLlChoice3;

    public ImageView getIvBg() {
        return mIvBg;
    }

    public TextView getTvCheapTitle1() {
        return mTvCheapTitle1;
    }

    public TextView getTvCheapDesc1() {
        return mTvCheapDesc1;
    }

    public ImageView getIvChoiceGoods1() {
        return mIvChoiceGoods1;
    }

    public LinearLayout getLlChoice1() {
        return mLlChoice1;
    }

    public TextView getTvCheapTitle2() {
        return mTvCheapTitle2;
    }

    public TextView getTvCheapDesc2() {
        return mTvCheapDesc2;
    }

    public ImageView getIvChoiceGoods2() {
        return mIvChoiceGoods2;
    }

    public LinearLayout getLlChoice2() {
        return mLlChoice2;
    }

    public TextView getTvCheapTitle3() {
        return mTvCheapTitle3;
    }

    public TextView getTvCheapDesc3() {
        return mTvCheapDesc3;
    }

    public ImageView getIvChoiceGoods3() {
        return mIvChoiceGoods3;
    }

    public LinearLayout getLlChoice3() {
        return mLlChoice3;
    }

    private OnClickListener onClickListener;

    public AlgChoiceHeaderHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener) {
        super(itemView, listener);
        this.onClickListener = onClickListener;
    }
    public AlgChoiceHeaderHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
    @OnClick({R.id.ll_goods1, R.id.ll_goods2, R.id.ll_goods3, R.id.ll_goods4, R.id.ll_goods5, R.id.ll_goods6, R.id.ll_goods7, R.id.ll_goods8, R.id.ll_choice_1, R.id.ll_choice_2, R.id.ll_choice_3})
    public void onViewClicked(View view) {
        if (onClickListener!=null){
            onClickListener.setOnClickListener(view,getAdapterPosition());
        }

    }
}
