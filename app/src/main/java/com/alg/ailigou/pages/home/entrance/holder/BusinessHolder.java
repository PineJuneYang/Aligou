package com.alg.ailigou.pages.home.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.holder
 * Created by Chris Chen on 2017/7/24 14:39.
 * Explain:联盟商家
 */

public class BusinessHolder extends BaseRecyclerHolder{
    @BindView(R.id.iv_home_business_image)
    public ImageView image;//图片
    @BindView(R.id.tv_home_business_title)
    public TextView title;//商户名称
    @BindView(R.id.tv_home_business_contacts_name)
    public TextView contacts;//联系人姓名
    @BindView(R.id.tv_home_business_contacts_phone)
    public TextView phone;//联系人电话
    @BindView(R.id.tv_home_business_address)
    public TextView address;//商户地址

    public BusinessHolder(View itemView) {
        super(itemView);
    }
}
