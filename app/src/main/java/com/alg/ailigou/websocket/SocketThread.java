package com.alg.ailigou.websocket;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.net.URI;

/**
 * AiligouApp
 * com.alg.ailigou.websocket
 * Created by Chris Chen on 2017/7/25 10:52.
 * Explain:WebSocket线程
 */

public class SocketThread extends Thread {
    private String WEBSOCKET_SERVER_URL = "ws://192.168.1.107:8081/WebSocketServer/chatserver";

    public boolean isClose;//关闭标记，主要是为发送心跳包判断状态
    private BaseSocketClient mClient;

    public SocketThread(Context context) {
        try {
            mClient = new BaseSocketClient(new URI(WEBSOCKET_SERVER_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        connect();//连接服务器
//        //注释是因为不要心跳也可以实现基本功能
//        while (!isClose) {
//            try {
//                //发送心跳包
//                mClient.send("1");
//                SystemClock.sleep(30000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 连接
     */
    private void connect() {
        try {
            mClient.connectBlocking();
            Logger.d("连接成功");
        } catch (Exception e) {
            Logger.d("连接失败");
            e.printStackTrace();
        }
    }

    /**
     * 断开连接
     */
    public void closeClient() {
        try {
            isClose = true;
            Logger.d("断开连接");
            mClient.closeBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseSocketClient getClient() {
        return mClient;
    }
}
