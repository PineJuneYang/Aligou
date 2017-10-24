package com.alg.ailigou.pages.union.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于  商家联盟
 */

public class UniconHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_home_business_image)
    ImageView mIvHomeBusinessImage;
    @BindView(R.id.tv_home_business_title)
    TextView mTvHomeBusinessTitle;
    @BindView(R.id.tv_home_business_contacts_name)
    TextView mTvHomeBusinessContactsName;
    @BindView(R.id.tv_home_business_contacts_phone)
    TextView mTvHomeBusinessContactsPhone;
    @BindView(R.id.tv_home_business_address)
    TextView mTvHomeBusinessAddress;

    public ImageView getIvHomeBusinessImage() {
        return mIvHomeBusinessImage;
    }

    public void setIvHomeBusinessImage(ImageView ivHomeBusinessImage) {
        mIvHomeBusinessImage = ivHomeBusinessImage;
    }

    public TextView getTvHomeBusinessTitle() {
        return mTvHomeBusinessTitle;
    }

    public void setTvHomeBusinessTitle(TextView tvHomeBusinessTitle) {
        mTvHomeBusinessTitle = tvHomeBusinessTitle;
    }

    public TextView getTvHomeBusinessContactsName() {
        return mTvHomeBusinessContactsName;
    }

    public void setTvHomeBusinessContactsName(TextView tvHomeBusinessContactsName) {
        mTvHomeBusinessContactsName = tvHomeBusinessContactsName;
    }

    public TextView getTvHomeBusinessContactsPhone() {
        return mTvHomeBusinessContactsPhone;
    }

    public void setTvHomeBusinessContactsPhone(TextView tvHomeBusinessContactsPhone) {
        mTvHomeBusinessContactsPhone = tvHomeBusinessContactsPhone;
    }

    public TextView getTvHomeBusinessAddress() {
        return mTvHomeBusinessAddress;
    }

    public void setTvHomeBusinessAddress(TextView tvHomeBusinessAddress) {
        mTvHomeBusinessAddress = tvHomeBusinessAddress;
    }

    public UniconHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
