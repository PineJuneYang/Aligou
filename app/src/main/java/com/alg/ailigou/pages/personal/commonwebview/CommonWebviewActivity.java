package com.alg.ailigou.pages.personal.commonwebview;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.alg.ailigou.pages.personal.consts.PersonalConsts.CHANGE_RULE;
import static com.alg.ailigou.pages.personal.consts.PersonalConsts.JOIN_PLATFORM;
import static com.alg.ailigou.pages.personal.consts.PersonalConsts.MEMBER_GRADE;
import static com.alg.ailigou.pages.personal.consts.PersonalConsts.USE_EXPLAIN;

/**
 * Created by 海航
 * on 2017/8/14.
 * 此类或接口用于  通过的只展示 webview的界面
 */

public class CommonWebviewActivity extends BaseMvpActivity {
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
    @BindView(R.id.webView)
    WebView mWebView;
    private int type;//不同type  代表不同页面
    private String url = "https://www.baidu.com/";

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_common_webview;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        initSetting();
        initView();
    }

    /**
     * 设置布局
     */
    private void initView() {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case MEMBER_GRADE://会员等级
                mTvBaseTitle.setText("会员等级");
                break;
            case JOIN_PLATFORM://入驻平台
                mTvBaseTitle.setText("入驻平台");
                break;
            case USE_EXPLAIN://使用说明
                mTvBaseTitle.setText("使用说明");
                break;
            case CHANGE_RULE://兑换规则
                mTvBaseTitle.setText("兑换规则");
                break;
            default://这个就是广告页过来的
                mTvBaseTitle.setText("广告");
                url = getIntent().getStringExtra("url");
                break;
        }
        mWebView.loadUrl(url);

    }

    /**
     * webview常用的设置
     */
    private void initSetting() {
        mWebView.requestFocusFromTouch();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setBuiltInZoomControls(false); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                Log.d("dugu",url);
            }
        });
    }

    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }
}
