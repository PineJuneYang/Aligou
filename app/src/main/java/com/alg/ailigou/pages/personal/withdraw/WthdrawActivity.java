package com.alg.ailigou.pages.personal.withdraw;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.addbankcardinfo.AddBankCardInfoActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/5.
 * 此类或接口用于 利购券  提现
 */

public class WthdrawActivity extends BaseMvpActivity {
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_athdraw_details)
    TextView mTvAthdrawDetails;
    @BindView(R.id.rl_bank_card)
    RelativeLayout mRlBankCard;
    @BindView(R.id.tv_overage)
    TextView mTvOverage;
    @BindView(R.id.et_athdraw_number)
    EditText mEtAthdrawNumber;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    @BindView(R.id.rl_base_title)
    RelativeLayout mRlTitle;

    private double number;//提现金额

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("提现");
        mTvBaseTitle.setTextColor(Color.BLACK);
        mTvBaseEdit.setTextColor(Color.BLACK);
        mTvBaseEdit.setText("提现记录");
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        mTvBaseEdit.setVisibility(View.VISIBLE);
        mEtAthdrawNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Double.parseDouble(mEtAthdrawNumber.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_wthdraw;
    }


    @OnClick({R.id.ll_base_back, R.id.rl_bank_card, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.rl_bank_card:
                startActivity(AddBankCardInfoActivity.class);
                break;
            case R.id.tv_sure:

                break;
        }
    }
}
