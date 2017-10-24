package com.alg.ailigou.pages.home.make;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.LogUtils;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.websocket.BaseSocketClient;
import com.alg.ailigou.websocket.MessageModel;
import com.alg.ailigou.websocket.SocketThread;
import com.alg.ailigou.websocket.WebSocketProtocol;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/9/1
 * 此类或接口用于
 */

public class BillDetailActivity extends BaseMvpActivity implements IntentKeys, BillDetailContracts.View, BaseSocketClient.OnHandMessage {

    @Inject
    BillDetailPresenter presenter;
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
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    @BindView(R.id.tv_home_bill_detail_number)
    TextView tvHomeBillDetailNumber;
    @BindView(R.id.tv_home_bill_detail_business_name)
    TextView tvHomeBillDetailBusinessName;
    @BindView(R.id.tv_home_bill_detail_business_telphone)
    TextView tvHomeBillDetailBusinessTelphone;
    @BindView(R.id.tv_home_bill_detail_member_account)
    TextView tvHomeBillDetailMemberAccount;
    @BindView(R.id.tv_home_bill_detail_member_telphone)
    TextView tvHomeBillDetailMemberTelphone;
    @BindView(R.id.tv_home_bill_detail_store_name)
    TextView tvHomeBillDetailStoreName;
    @BindView(R.id.iv_home_bill_detail_minus)
    ImageView ivHomeBillDetailMinus;

    @BindView(R.id.iv_home_bill_detail_add)
    ImageView ivHomeBillDetailAdd;
    @BindView(R.id.tv_home_bill_detail_total_money)
    TextView tvHomeBillDetailTotalMoney;
    @BindView(R.id.tv_home_bill_detail_charge)
    TextView tvHomeBillDetailCharge;
    @BindView(R.id.tv_home_bill_detail_submit)
    TextView tvHomeBillDetailSubmit;
    @BindView(R.id.et_home_bill_detail_count)
    EditText etHomeBillDetailCount;
    private String scanResult = "";
    private int currentCount  = 1 ;

    private SocketThread socketThread;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        scanResult = intent.getStringExtra(SCAN_RESULT);

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_bill_detail;
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();

        tvBaseTitle.setText("账单详情");




        //订单号
        tvHomeBillDetailNumber.setText(scanResult);

        //会员用户的信息是从本地区读取的


        //根据商家订单号,请求网络获取用商家详情
        presenter.loadUnionDetail(scanResult);

        etHomeBillDetailCount.setText(currentCount+"");

        initWebSocket();


    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
    }


    @OnClick({R.id.ll_base_back, R.id.tv_home_bill_detail_submit, R.id.iv_home_bill_detail_add, R.id.iv_home_bill_detail_minus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_home_bill_detail_submit:
                sendMessage();
                break;

            case R.id.iv_home_bill_detail_minus:
                currentCount = Integer.parseInt(etHomeBillDetailCount.getText().toString().trim());
                if (currentCount>1){
                    currentCount--;
                    etHomeBillDetailCount.setText(currentCount+"");
                }

                break;

            case R.id.iv_home_bill_detail_add:
                currentCount = Integer.parseInt(etHomeBillDetailCount.getText().toString().trim());
                currentCount++;
                etHomeBillDetailCount.setText(currentCount+"");
                break;
        }
    }

    @Override
    public void updateUnionData(UnionModel data) {
        if (data != null) {
            tvHomeBillDetailBusinessName.setText(data.storeName);
            tvHomeBillDetailBusinessTelphone.setText(data.telNumber);
        }
    }


    /**
     * 初始化WebSocket
     */
    private void initWebSocket() {
        socketThread = new SocketThread(this);
        socketThread.getClient().setOnHandMessage(this);
        socketThread.start();
    }

    /**
     * 发送消息
     */
    private void sendMessage() {
        MessageModel messageModel = new MessageModel();
        WebSocketProtocol protocol = new WebSocketProtocol();
        protocol.type = 1;
        protocol.sendId = 123;
        protocol.isBroadCast = false;
        protocol.format = 3;
        List<Integer> acceptIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            acceptIds.add(new Random().nextInt(100000));
        }
        protocol.acceptIds = acceptIds;
        messageModel.protocol = protocol;
        messageModel.content = scanResult;
        socketThread.getClient().send(new Gson().toJson(messageModel));
    }

    @Override
    public void onHandMessage(final String message) {
        //放在主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tvBill.append(message + "\n");
                LogUtils.d(message);
            }
        });
    }


}
