package com.alg.ailigou.pages.personal.aboutaligo;

import android.content.Intent;
import android.net.Uri;
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
 * on 2017/8/3
 * 此类或接口用于
 */

public class AboutAligoActivity extends BaseMvpActivity {
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_aligo_logo)
    ImageView ivAligoLogo;
    @BindView(R.id.tv_aligo_name)
    TextView tvAligoName;
    @BindView(R.id.linear_personal_aboutaligo)
    LinearLayout linearPersonalAboutaligo;
    @BindView(R.id.relative_about_aligo_comment)
    RelativeLayout relativeAboutAligoComment;
    @BindView(R.id.relative_about_aligo_introduce)
    RelativeLayout relativeAboutAligoIntroduce;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_about_aligo;
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();

    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        tvBaseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        tvBaseTitle.setTextColor(getResources().getColor(R.color.alg_common_37));
        ivBaseBack.setBackgroundResource(R.drawable.alg_home_commidity_detail_arrow);

        tvBaseTitle.setText("关于爱利购");
    }


    @OnClick({R.id.relative_about_aligo_comment, R.id.relative_about_aligo_introduce, R.id.ll_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relative_about_aligo_comment:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.relative_about_aligo_introduce:

                break;


            case R.id.ll_base_back:
                finish();
                break;
        }
    }


    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
