package com.alg.ailigou.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alg.ailigou.R;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class IosDialog extends Dialog implements View.OnClickListener {

    private String contentText = "";
    private String sureText = "";
    private String cancelText = "";

    private IosDialogCallBack iosDialogCallBack;

    public void setDialogContentCallBack(IosDialogContentCallBack dialogContentCallBack) {
        this.dialogContentCallBack = dialogContentCallBack;
    }

    private IosDialogContentCallBack dialogContentCallBack;

    public void setIosDialogCallBack(IosDialogCallBack iosDialogCallBack) {
        this.iosDialogCallBack = iosDialogCallBack;
    }

    public void setContent(String content) {
        this.contentText = content;
    }

    public void setSureText(String sureText) {
        this.sureText = sureText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public IosDialog(@NonNull Context context) {
        super(context);
    }

    public IosDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected IosDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alg_comon_dialog_ios);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        TextView content = (TextView) findViewById(R.id.tv_ios_dialog_content);
        TextView positive = (TextView) findViewById(R.id.tv_ios_dialog_postive);
        TextView negative = (TextView) findViewById(R.id.tv_ios_dialog_negative);

        content.setText(contentText);
        positive.setText(sureText);
        if (cancelText != null && !TextUtils.isEmpty(cancelText)) {
            negative.setText(cancelText);
        }

        content.setOnClickListener(this);
        positive.setOnClickListener(this);
        negative.setOnClickListener(this);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_ios_dialog_postive:
                if (iosDialogCallBack != null) {
                    iosDialogCallBack.onSure();
                }


                break;

            case R.id.tv_ios_dialog_negative:
                if (iosDialogCallBack != null) {
                    iosDialogCallBack.onCancle();
                }
                break;
            case R.id.tv_ios_dialog_content:
                if (dialogContentCallBack != null) {
                    dialogContentCallBack.onContent();
                }
                break;
        }
        dismiss();
    }


    public interface IosDialogCallBack {

        void onSure();

        void onCancle();

    }

    /**
     * 点击 最上面那个按钮
     */
    public interface IosDialogContentCallBack {
        void onContent();

    }

}
