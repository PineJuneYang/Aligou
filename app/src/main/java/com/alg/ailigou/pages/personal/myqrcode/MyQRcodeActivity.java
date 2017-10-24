package com.alg.ailigou.pages.personal.myqrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 海航
 * on 2017/8/4.
 * 此类或接口用于
 */

public class MyQRcodeActivity extends BaseMvpActivity {
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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;

    private Bitmap mImage;
    private Bitmap mLogo;
    private Subscription mSubscription;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
        mTvBaseTitle.setTextColor(Color.BLACK);
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        mTvBaseTitle.setText("我的二维码");
        initQrCodeImg();
    }


    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_qr_code;
    }


    @OnClick(R.id.ll_base_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 生成二维码
     */
    private void initQrCodeImg() {
        mSubscription = Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                //该方法是在子线程
                mImage = QRCodeEncoder.syncEncodeQRCode("https://www.baidu.com/", MeasureUtils.dp2px(185));
                subscriber.onNext(mImage);
                subscriber.onCompleted();
            }
        }).compose(RxUtils.<Bitmap>schedulerHelper()).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                //该方法是在主线程
                mIvQrCode.setBackground(new BitmapDrawable(mImage));
                ImageLoadUtils.setImageCircle(MyQRcodeActivity.this,"http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg",mIvIcon);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImage != null) {
            mImage.recycle();
            mImage = null;
        }
        if (mLogo != null) {
            mLogo.recycle();
            mLogo = null;
        }
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
