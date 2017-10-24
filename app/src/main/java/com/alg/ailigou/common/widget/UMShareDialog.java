package com.alg.ailigou.common.widget;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ShareModel;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import rx.functions.Action1;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于
 */

public class UMShareDialog extends Dialog implements View.OnClickListener {
    LinearLayout mLlWecaht;
    LinearLayout mLlWecahtFriends;
    LinearLayout mLlQq;
    LinearLayout mLlQqZone;
    LinearLayout mLlWeibo;
    private Context context;
    private ShareModel shareModel;

    public UMShareDialog(Context context, ShareModel shareModel) {
        super(context);
        this.context = context;
        this.shareModel = shareModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alg_comon_dialog_um_share);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        findView();
    }

    private void findView() {
        mLlWecaht = (LinearLayout) findViewById(R.id.ll_wecaht);
        mLlWecahtFriends = (LinearLayout) findViewById(R.id.ll_wecaht_friends);
        mLlQq = (LinearLayout) findViewById(R.id.ll_qq);
        mLlQqZone = (LinearLayout) findViewById(R.id.ll_qq_zone);
        mLlWeibo = (LinearLayout) findViewById(R.id.ll_weibo);
        mLlWecaht.setOnClickListener(this);
        mLlWecahtFriends.setOnClickListener(this);
        mLlQq.setOnClickListener(this);
        mLlQqZone.setOnClickListener(this);
        mLlWeibo.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        //用户设置的图片大小最好不要超过250k，缩略图不要超过18k
        final UMImage image;
        if (shareModel.imgID != 0) {
            image = new UMImage(context, shareModel.imgID);
        } else {
            image = new UMImage(context, R.drawable.alg_debug_icon_weibo_70_70);//默认的logo
        }
        final UMWeb web = new UMWeb(shareModel.url);
        web.setThumb(image);
        web.setTitle(shareModel.title);//标题
        web.setDescription(shareModel.desc);//描述
        /**
         * 先进行权限处理
         */
        RxPermissions.getInstance(context).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    switch (v.getId()) {
                        case R.id.ll_wecaht:
                            new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(web).setCallback(shareListener).share();
                            break;
                        case R.id.ll_wecaht_friends:
                            new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).setCallback(shareListener).share();
                            break;
                        case R.id.ll_qq:
                            new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QQ).withMedia(web).setCallback(shareListener).share();
                            break;
                        case R.id.ll_qq_zone:
                            new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QZONE).withMedia(web).setCallback(shareListener).share();
                            break;
                        case R.id.ll_weibo:
                            new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.SINA).withMedia(web).setCallback(shareListener).share();
                            break;
                    }
                }
            }
        });

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

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context, "分享成功", Toast.LENGTH_LONG).show();
            dismiss();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            dismiss();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //Toast.makeText(context,"取消了",Toast.LENGTH_LONG).show();

        }
    };
}
