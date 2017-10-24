package com.alg.ailigou.pages.personal.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;

/**
 * Created by 海航
 * on 2017/8/16.
 * 此类或接口用于
 */

public class CheckInDialog extends Dialog {
    private ImageView dismissImg;

    public CheckInDialog(@NonNull Context context) {
        this(context, R.style.alg_dialog);
    }

    public CheckInDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alg_dialog_personal_check_in);
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        dismissImg = (ImageView) findViewById(R.id.iv_dismiss);
        dismissImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
