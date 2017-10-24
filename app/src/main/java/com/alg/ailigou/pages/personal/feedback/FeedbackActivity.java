package com.alg.ailigou.pages.personal.feedback;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/14.
 * 此类或接口用于  意见反馈
 */

public class FeedbackActivity extends BaseMvpActivity {
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.et_feedbook)
    EditText mEtFeedbook;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private String feedback;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_feedbook;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mEtFeedbook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                feedback = mEtFeedbook.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ll_base_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;

            case R.id.tv_submit:
                if (feedback == null || feedback.length() < 5) {
                    ToastUtils.showToast(this, "请至少输入5个字!");
                } else {
                    submitFeedback();
                }

                break;
        }
    }

    /**
     * 联网发送意见反馈
     */
    private void submitFeedback() {

    }
}
