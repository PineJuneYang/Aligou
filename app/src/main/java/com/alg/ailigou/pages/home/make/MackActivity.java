package com.alg.ailigou.pages.home.make;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.JsonUtils;
import com.alg.ailigou.library.utils.LogUtils;
import com.alg.ailigou.websocket.BaseSocketClient;
import com.alg.ailigou.websocket.MessageModel;
import com.alg.ailigou.websocket.SocketThread;
import com.alg.ailigou.websocket.WebSocketProtocol;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.mack
 * Created by Chris Chen on 2017/7/25 10:42.
 * Explain:做单
 */

public class MackActivity extends BaseMvpActivity implements IntentKeys, BaseSocketClient.OnHandMessage {
    @BindView(R.id.tv_home_make_business)
    TextView tvBusiness;//商户信息
    @BindView(R.id.tv_home_make_consumer)
    TextView tvConcumer;//消费者
    @BindView(R.id.tv_home_make_bill)
    TextView tvBill;//账单信息
    @BindView(R.id.tv_home_make_commit)
    TextView tvCommit;//提交确认

    private String businessId;
    private SocketThread socketThread;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_make_make;
    }

    @Override
    protected void initViewAndListener() {
        //获取传递的信息
        Intent intent = getIntent();
        businessId = intent.getStringExtra(SCAN_RESULT);
        //填充商户信息
        tvBusiness.setText("商家的id是：" + businessId);
        //填充消费者信息
        tvConcumer.setText("消费者的id是：" + "从本地读取的登录信息");
        //填写账单信息，选择商品信息
        tvBill.setText("商品信息需要选择维护和填写");
        //测试WebSocket通信
        initWebSocket();
        loadData();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        //请求商户信息
        NetCallback<UnionModel> callback = new NetCallback<UnionModel>() {
            @Override
            protected void onComplete(NetResponse<UnionModel> netResponse) {
                if (netResponse.isSuccess) {
                    tvBusiness.setText(JsonUtils.objToJson(netResponse.data));
                }
            }
        };
        UniconApi.getUnionDetailsData(Integer.parseInt(businessId), callback);
        //请求消费者信息
        //消费者信息从本地存储的登录信息中获取
        //或者从服务器再请求一次


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
        messageModel.content = businessId;
        socketThread.getClient().send(new Gson().toJson(messageModel));
    }

    @Override
    public void onHandMessage(final String message) {
        //放在主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvBill.append(message + "\n");
                LogUtils.d(message);
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_home_make_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home_make_commit:
                //发送消息
                sendMessage();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        //关闭连接线程
        socketThread.closeClient();
        super.onDestroy();
    }


}
