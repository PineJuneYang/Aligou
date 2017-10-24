package com.alg.ailigou.pages.personal.addbankcardinfo;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.BankModel;
import com.alg.ailigou.common.widget.IosDialog;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.KeyBoardUtils;
import com.alg.ailigou.pages.personal.addbankcard.AddBankCardActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.bigkoo.pickerview.OptionsPickerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/3.
 * 此类或接口用于  点击完添加银行卡,跳转到的银行卡编辑页面  银行卡详情也是这个界面
 */

public class AddBankCardInfoActivity extends BaseMvpActivity implements AddBankCardInfoContrats.View {
    @Inject
    AddBankCardInfoPresenter mPresenter;
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
    @BindView(R.id.iv_name)
    ImageView mIvName;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.iv_id_number)
    ImageView mIvIdNumber;
    @BindView(R.id.et_id_number)
    EditText mEtIdNumber;
    @BindView(R.id.ll_id_number)
    LinearLayout mLlIdNumber;
    @BindView(R.id.iv_bank)
    ImageView mIvBank;
    @BindView(R.id.iv_arrow_right)
    ImageView mIvBankArrowRight;
    @BindView(R.id.et_bank)
    TextView mEtBank;
    @BindView(R.id.ll_bank)
    LinearLayout mLlBank;
    @BindView(R.id.iv_bank_address)
    ImageView mIvBankAddress;
    @BindView(R.id.et_bank_address)
    TextView mEtBankAddress;
    @BindView(R.id.ll_bank_address)
    LinearLayout mLlBankAddress;
    @BindView(R.id.iv_bank_branch)
    ImageView mIvBankBranch;
    @BindView(R.id.et_bank_branch)
    EditText mEtBankBranch;
    @BindView(R.id.ll_bank_branch)
    LinearLayout mLlBankBranch;
    @BindView(R.id.iv_bank_number)
    ImageView mIvBankNumber;
    @BindView(R.id.et_bank_number)
    EditText mEtBankNumber;
    @BindView(R.id.ll_bank_number)
    LinearLayout mLlBankNumber;
    @BindView(R.id.tv_unbundled)
    TextView mTvUnbundled;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    @BindView(R.id.tv_bank_desc)
    TextView mTvBankDesc;
    private int bankStatusType;//是否存在银行卡  如果存在  那么为展示页,若不存在就是添加页
    private OptionsPickerView pickerView;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
        bankStatusType = getIntent().getIntExtra("bankStatusType", PersonalConsts.HAS_NO_BANK_NUMBER);
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        mTvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        if (bankStatusType == PersonalConsts.HAS_NO_BANK_NUMBER) {//添加银行卡
            mTvSure.setVisibility(View.VISIBLE);
            mLlBaseEdit.setVisibility(View.GONE);
            mTvUnbundled.setVisibility(View.GONE);
            mIvBankArrowRight.setVisibility(View.VISIBLE);
            mTvBankDesc.setVisibility(View.VISIBLE);
            mTvBaseTitle.setText("添加银行卡");
            changeViewFocus(true);
        } else {//展示银行卡信息
            mTvBankDesc.setVisibility(View.GONE);
            mTvSure.setVisibility(View.GONE);
            mLlBaseEdit.setVisibility(View.GONE);
            mTvUnbundled.setVisibility(View.GONE);
            mTvBaseEdit.setText("修改");
            mIvBankArrowRight.setVisibility(View.GONE);
            mTvBaseTitle.setText("我的银行卡");
            changeViewFocus(false);
            setViewData((BankModel) getIntent().getSerializableExtra("bankData"));
        }
    }

    /**
     * 不同状态下 edittext焦点的变化
     *
     * @param b
     */
    private void changeViewFocus(boolean b) {
        mEtIdNumber.setEnabled(b);
        mEtBankAddress.setEnabled(b);
        mEtBankNumber.setEnabled(b);
        mEtBankBranch.setEnabled(b);
        mEtName.setEnabled(b);
        mLlBank.setEnabled(b);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_add_bank_card_info;
    }


    @OnClick({R.id.ll_base_back, R.id.ll_base_edit, R.id.ll_bank, R.id.tv_unbundled, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_base_edit:
                if (mTvBaseEdit.getText().equals("修改")) {
                    mTvBaseEdit.setText("完成");
                    mIvBankArrowRight.setVisibility(View.VISIBLE);
                    mTvUnbundled.setVisibility(View.GONE);
                    changeViewFocus(true);
                } else {
                    mPresenter.commitBankInfo(getSubmitdata(), PersonalConsts.TYPE_BANK_EDIT_COMMIT);
                }
                break;
            case R.id.ll_bank:
                KeyBoardUtils.HideKeyboard((View) mLlBank.getParent(), this);
                pickerView = PickerUtils.getCommonPickerView(this, "银行选择", PersonalConsts.getBankList(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        mEtBank.setText(PersonalConsts.getBankList().get(options1));
                        pickerView.dismiss();
                    }
                });
                pickerView.show();
                break;
            case R.id.tv_unbundled:
                // showDeleteBankDialog();
                break;
            case R.id.tv_sure:
                mPresenter.commitBankInfo(getSubmitdata(), PersonalConsts.TYPE_BANK_ADD_COMMIT);
                break;
        }
    }


    /**
     * 吧数据添加到model里面提交
     */
    private BankModel getSubmitdata() {
        BankModel model = new BankModel();
        model.bankBranchAddress = mEtBankBranch.getText().toString().trim();
        model.name = mEtName.getText().toString().trim();
        model.bankName = mEtBank.getText().toString().trim();
        model.idNumber = mEtIdNumber.getText().toString().trim();
        model.bankNumber = mEtBankNumber.getText().toString().trim();
        return model;
    }

    @Override
    public void setViewData(BankModel model) {
        mEtBankBranch.setText(model.bankBranchAddress);
        mEtName.setText(model.name);
        mEtBank.setText(model.bankName);
        mEtIdNumber.setText(model.idNumber);
        mEtBankNumber.setText(model.bankNumber);
    }

    @Override
    public void commitFinish(int type) {
        if (type == PersonalConsts.TYPE_BANK_ADD_COMMIT) {
            Intent intent = new Intent(this, AddBankCardInfoActivity.class);
            intent.putExtra("bankStatusType", PersonalConsts.ADD_BANK_SUCCESS);
            intent.putExtra("bankData", getSubmitdata());
            startActivity(intent);
            finish();
        } else if (type == PersonalConsts.TYPE_BANK_EDIT_COMMIT) {
            changeViewFocus(false);
            mTvBaseEdit.setText("修改");
            mTvUnbundled.setVisibility(View.VISIBLE);
            mIvBankArrowRight.setVisibility(View.GONE);
        }
    }

    IosDialog dialog;

    @Override
    public void showDeleteBankDialog() {
        dialog = new IosDialog(this);
        dialog.setContent("确实解除该银行卡的绑定么?");
        dialog.setSureText("解除绑定");
        dialog.setCancelText("取消");
        dialog.show();
        dialog.setIosDialogCallBack(new IosDialog.IosDialogCallBack() {
            @Override
            public void onSure() {

                mPresenter.removeBankCard();
            }

            @Override
            public void onCancle() {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void dismissDeleteBankDialog() {
        dialog.dismiss();
    }

    @Override
    public void toAddCardActivity() {
        startActivity(AddBankCardActivity.class);
        finish();
    }
}
