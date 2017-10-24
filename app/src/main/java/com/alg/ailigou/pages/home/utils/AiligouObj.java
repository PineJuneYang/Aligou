package com.alg.ailigou.pages.home.utils;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.utils
 * Created by Chris Chen on 2017/7/12 17:16.
 * Explain:用于WebView和前端js进行交互的object
 */

public class AiligouObj extends Object {
    private Activity mActivity;
    private WebView mWebView;
    private TextView tvInfo;

    public AiligouObj(Activity activity, WebView mWebView) {
        this.mActivity = activity;
        this.mWebView = mWebView;
    }

    /**
     * html中的JavaScript调用Java方法
     * 注解是必须的
     *
     * @return
     */
    @JavascriptInterface
    public String jsCallJava() {
        return "JavaScript调用Java方法";
    }

    /**
     * html中的JavaScript调用带参数的Java方法
     * 注解是必须的
     *
     * @return
     */
    @JavascriptInterface
    public String jsCallJavaWithParams(String var) {
        Toast.makeText(mActivity, var, Toast.LENGTH_SHORT).show();
        return "JavaScript调用带参数的Java方法" + var;
    }

    /**
     * java调用html中的方法
     */
    @JavascriptInterface
    public void javaCallJavaScript() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript:show()");

            }
        });
    }

    /**
     * java调用html中的方法，并且传递参数
     */
    @JavascriptInterface
    public void javaCallJavaScriptWithParams() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript:showWithParam('baoge')");
            }
        });
    }

    /**
     * java调用html中的方法，从js获取一个数据，显示在文本框中.这需要两个方法配合
     */
    @JavascriptInterface
    public void javaGetDataFromJs() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript:sendData()");
            }
        });
    }

    /**
     * 配合上面
     */

    @JavascriptInterface
    public String jsSendDataToJava(String var) {
        tvInfo.setText(var);
        return "JavaScript收到请求后发送给Java的数据就是:" + var;
    }

    /**
     * js调用Java中的方法，获取一个数据(包名)
     */
    @JavascriptInterface
    public String jsGetDataFromJava() {
        return mActivity.getPackageName();
    }

    public void setTvInfo(TextView tvInfo) {
        this.tvInfo = tvInfo;
    }
}
