package com.alg.ailigou.pages.personal.memberlevel;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/9/5
 * 此类或接口用于
 */

public class MemeberLevelActivity extends BaseMvpActivity {

    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    @BindView(R.id.iv_memeber_level)
    ImageView ivMemeberLevel;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.iv_diamond)
    ImageView ivDiamond;
    @BindView(R.id.iv_platinum)
    ImageView ivPlatinum;
    @BindView(R.id.iv_gold)
    ImageView ivGold;
    @BindView(R.id.iv_sliver)
    ImageView ivSliver;
    @BindView(R.id.iv_copper)
    ImageView ivCopper;
    @BindView(R.id.iv_register)
    ImageView ivRegister;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_memeber_level;
    }

    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();

        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        tvBaseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        tvBaseTitle.setTextColor(getResources().getColor(R.color.alg_common_37));
        ivBaseBack.setBackgroundResource(R.drawable.alg_home_commidity_detail_arrow);


        tvBaseTitle.setText("会员等级");


    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
    }



    @OnClick({R.id.iv_diamond, R.id.iv_platinum, R.id.iv_gold, R.id.iv_sliver, R.id.iv_copper, R.id.iv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_diamond:
                break;
            case R.id.iv_platinum:
                break;
            case R.id.iv_gold:
                break;
            case R.id.iv_sliver:
                break;
            case R.id.iv_copper:
                break;
            case R.id.iv_register:
                break;
        }
    }
}
