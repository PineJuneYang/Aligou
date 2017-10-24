package com.alg.ailigou.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alg.ailigou.R;


/**
 * Created by hai on 2017/4/11.
 */

public class CommonDialog extends Dialog {
    private Button sure;

    public Button getCancel() {
        return cancel;
    }

    public Button getSure() {
        return sure;
    }

    private Button cancel;
    private TextView textView;
    private String desc;
    private Context context;

    public CommonDialog(Context context) {
        super(context, R.style.alg_dialog);
        this.context = context;
    }

    public CommonDialog(Context context, String des) {
        super(context, R.style.alg_dialog);
        this.desc = des;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alg_common_dialog);
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        sure = (Button) findViewById(R.id.btn_sure);
        cancel = (Button) findViewById(R.id.btn_cancle);
        textView = (TextView) findViewById(R.id.tv_dialog_des);
        if (desc != null) {
            textView.setText(desc);
        }

    }


}
