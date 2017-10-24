package com.alg.ailigou.pages.home.make;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.pages.home.utils.HomeUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import rx.functions.Action1;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.scan
 * Created by Chris Chen on 2017/7/24 16:17.
 * Explain:
 */

public class ScanActivity extends BaseMvpActivity implements IntentKeys, QRCodeView.Delegate {
    private static final int RESULT_CODE = 1;

    @BindView(R.id.zxv_home_qrcode_scan)
    ZXingView mZXingView;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_qrcode_scan;
    }

    @Override
    protected void initViewAndListener() {
        mZXingView.setDelegate(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
//        MessageUtils.debug(result);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);

        if (HomeUtils.isNumeric(result)) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_RESULT, result);
            //这里可以选择向调用页面返回结果
            //可扩展，根据业务代码来区分是该跳转还是该返回结果
//        this.setResult(RESULT_CODE, intent);
            intent.setClass(this, BillDetailActivity.class);//带着获取的消费者ID或者Token，跳转到做单页面
            startActivity(intent);
            finish();
        } else {
            MessageUtils.debug("你说，" + result + "算什么id？");
            mZXingView.startSpot();//可以多次识别
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        MessageUtils.show("无法识别");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 23) {
            RxPermissions.getInstance(this)
                    .request(Manifest.permission.CAMERA)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {
                                mZXingView.startCamera();
                                mZXingView.startSpotDelay(500);
                                MessageUtils.debug("6.0获取权限成功");
                            } else {
                                MessageUtils.debug("权限被拒绝");
                                finish();
                            }
                        }
                    });
        } else {
            MessageUtils.debug
                    ("获取权限成功");
            mZXingView.startCamera();
            mZXingView.startSpotDelay(500);
        }
    }

    @Override
    protected void onStop() {
        if (mZXingView != null) {
            mZXingView.stopSpot();
            mZXingView.stopCamera();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mZXingView != null) {
            mZXingView.onDestroy();
            mZXingView = null;
        }
        super.onDestroy();
    }
}
