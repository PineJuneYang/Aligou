package com.alg.ailigou.common.widget;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.alg.ailigou.R;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.home.utils.AiligouObj;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/11 17:05.
 * Explain:加载网页的泡泡窗口
 */

public class WebPopupWindow extends PopupWindow {
    private Context mContext;
    private WebView webview;

    public WebPopupWindow(Context context,int width, int height) {
        super(width, height);
        mContext=context;
        initView();
    }

    public WebPopupWindow(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.alg_view_home_notice, null);
        setContentView(contentView);
        setOutsideTouchable(true);

        webview = (WebView) contentView.findViewById(R.id.web_home_notice);
        RelativeLayout bg = (RelativeLayout) contentView.findViewById(R.id.rl_home_pop_bg);
        ImageView close = (ImageView) contentView.findViewById(R.id.iv_home_pop_close);
        webview.getLayoutParams().width = MeasureUtils.dp2px(280);
        webview.getLayoutParams().height = MeasureUtils.dp2px(400);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new AiligouObj((Activity) mContext, webview), "ailigouobj");
        webview.setWebViewClient(new WebViewClient());

    }

    public void show(View parentView,String url){
        webview.loadUrl(url);
        showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }
}
