package com.alg.ailigou.pages.personal.refundsdetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 玖泞
 * on 2017/8/17
 * 此类或接口用于  退款详情
 */

public class RefundsDetailActivity extends BaseMvpActivity {


    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.iv_refunds_detail_icon)
    ImageView ivRefundsDetailIcon;
    @BindView(R.id.tv_refunds_detail_brand)
    TextView tvRefundsDetailBrand;
    @BindView(R.id.tv_refunds_detail_specification)
    TextView tvRefundsDetailSpecification;
    @BindView(R.id.tv_refunds_detail_refunds_number)
    TextView tvRefundsDetailRefundsNumber;
    @BindView(R.id.tv_refunds_detail_refunds_apply_date)
    TextView tvRefundsDetailRefundsApplyDate;
    @BindView(R.id.tv_refunds_detail_refunds_money)
    TextView tvRefundsDetailRefundsMoney;
    @BindView(R.id.tv_refunds_detail_refunds_reason)
    TextView tvRefundsDetailRefundsReason;
    @BindView(R.id.tv_refunds_detail_modity_apply)
    TextView tvRefundsDetailModityApply;
    @BindView(R.id.tv_refunds_detail_online_consult)
    TextView tvRefundsDetailOnlineConsult;
    @BindView(R.id.tv_last_time)
    TextView tvLastTime;
    private long orderId;
    private Subscription mTimerSubscribe;
    private ReturnGoodsData returnGoodsData;
    long countdown;

    @Override
    protected void initBase() {
        super.initBase();
        orderId = getIntent().getLongExtra("orderId",orderId);//上个界面过来需要传过来orderId
    }

    @Override
    protected void initInjector() {
        super.initInjector();
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvBaseTitle.setText("退款申请");
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        getData();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_refunds_detail;
    }


    @OnClick({R.id.ll_base_back, R.id.tv_refunds_detail_modity_apply, R.id.tv_refunds_detail_online_consult})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_refunds_detail_modity_apply:

                break;
            case R.id.tv_refunds_detail_online_consult:
                break;
        }
    }


    /**
     * 倒计时
     */
    private void setCountTimer() {
        //1秒钟执行这个方法..interval(0,1, TimeUnit.SECONDS)
        mTimerSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                String s = "";
                if (returnGoodsData != null) {
                    returnGoodsData.lastReturnGoodsTime--;
                    s = TimeUtils.formatDayHourMinuteSecond(returnGoodsData.lastReturnGoodsTime);
                }
                return s;
            }
        }).compose(RxUtils.<String>schedulerHelper()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tvLastTime.setText(s);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimerSubscribe != null && !mTimerSubscribe.isUnsubscribed()) {
            mTimerSubscribe.unsubscribe();
        }
    }

    public void getData() {
        UserApi.retReturnGoodsData(orderId, new NetCallback<ReturnGoodsData>() {


            @Override
            protected void onComplete(NetResponse<ReturnGoodsData> netResponse) {
                if (netResponse.isSuccess){
                    returnGoodsData=netResponse.data;
                    setViewData(returnGoodsData);
                }
            }
        });
    }

    /**
     * 拿到数据显示
     * @param data
     */
    private void setViewData(ReturnGoodsData data) {
    }
}
