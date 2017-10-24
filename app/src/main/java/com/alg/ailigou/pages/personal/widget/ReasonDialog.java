package com.alg.ailigou.pages.personal.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.widget.adapter.ReasonDialogAdapter;

/**
 * Created by 海航
 * on 2017/8/18.
 * 此类或接口用于
 */

public class ReasonDialog extends Dialog {
    private Context context;

    public ReasonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public ReasonDialog(@NonNull Context context) {
        this(context, R.style.alg_dialog);
    }


    private RecyclerView recyler_view;
    private TextView tv_cancel;
    private ReasonDialogAdapter mAdapter;

    public ReasonDialogAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alg_dialog_personal_reason);
        bindViews();
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void bindViews() {
        recyler_view = (RecyclerView) findViewById(R.id.recyler_view);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        recyler_view.setHasFixedSize(true);
        recyler_view.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new ReasonDialogAdapter(PersonalConsts.getRefundsReasonList(), context);
        recyler_view.setAdapter(mAdapter);
    }
    /**
     * 全屏显示
     */
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
}
