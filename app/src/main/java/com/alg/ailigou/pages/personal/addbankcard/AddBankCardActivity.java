package com.alg.ailigou.pages.personal.addbankcard;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.addbankcardinfo.AddBankCardInfoActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/3.
 * 此类或接口用于  添加银行卡
 */

public class AddBankCardActivity extends BaseMvpActivity {
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.ll_add)
    LinearLayout mLlAdd;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_add_bank_card;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("添加银行卡");
        mTvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    @OnClick({R.id.ll_base_back, R.id.ll_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_add:
                Intent intent = new Intent(this, AddBankCardInfoActivity.class);
                intent.putExtra("bankStatusType", PersonalConsts.HAS_NO_BANK_NUMBER);
                startActivity(intent);
                break;
        }
    }
}
