package com.alg.ailigou.pages.personal.signin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.utils.PopuWindowUtils;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.common.utils.StatusBarUtil;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by 海航
 * on 2017/8/31.
 * 此类或接口用于签到
 */

public class SignInActivity extends BaseMvpActivity {
    @BindView(R.id.iv_zoom)

    ImageView mIvZoom;
    @BindView(R.id.tv_sign_in)
    TextView mTvSignIn;
    @BindView(R.id.ll_zoom)
    LinearLayout llZoom;
    private Subscription mWindowSubscription;
    private Subscription mWindowDismissSubscription;
    private PopupWindow mWindow;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_sign_in;
    }

    @Override
    protected void initStatueBar() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        initAnim();
    }

    private void initAnim() {
        startPropertyAnim();
        mWindowSubscription = Observable.timer(2600, TimeUnit.MILLISECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                showWindow();
                dismissWindow();
            }
        });

    }

    /**
     * 1秒后消失
     */
    private void dismissWindow() {
        mWindowDismissSubscription=Observable.timer(2000, TimeUnit.MILLISECONDS).compose(RxUtils.<Long>schedulerHelper()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                mWindow.dismiss();
            }
        });
    }

    /**
     * 提示的window
     */
    private void showWindow() {
        ImageView contentview = new ImageView(this);
        contentview.setBackgroundResource(R.drawable.alg_personal_sign_in_window);
        mWindow = PopuWindowUtils.getPopuWindow(contentview, false);
        mWindow.setOutsideTouchable(false);
        mWindow.setAnimationStyle(R.style.popup_left_anim);
        mWindow.showAsDropDown(mTvSignIn, 0-MeasureUtils.dp2px(35),MeasureUtils.dp2px(25)-mIvZoom.getMeasuredHeight());
    }


    @OnClick(R.id.iv_zoom)
    public void onViewClicked() {

    }

    // 动画实际执行
    private void startPropertyAnim() {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mIvZoom, "scaleX", 1f, 1.11f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mIvZoom, "scaleY", 1f, 1.11f, 1f);
        anim1.setRepeatCount(2);//设置动画重复次数，这里-1代表无限
        anim2.setRepeatCount(2);//设置动画重复次数，这里-1代表无限
        animatorSet.setDuration(700);
        //anim.setRepeatMode(Animation.REVERSE);//设置动画循环模式。
        // 回调监听，可以有也可以无。
        animatorSet.play(anim1).with(anim2);//两个动画同时开始
        animatorSet.setStartDelay(500);
        // 正式开始启动执行动画
        animatorSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWindowSubscription != null && !mWindowSubscription.isUnsubscribed()) {
            mWindowSubscription.unsubscribe();
        }
    }


}
