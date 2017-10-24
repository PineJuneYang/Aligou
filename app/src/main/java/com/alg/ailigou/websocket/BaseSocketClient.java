package com.alg.ailigou.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * AiligouApp
 * com.alg.ailigou.websocket
 * Created by Chris Chen on 2017/7/25 10:54.
 * Explain:WebSocket客户端
 */

public class BaseSocketClient extends WebSocketClient {
    private OnHandMessage onHandMessage;

    public BaseSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        //通知UI更新，就加这么一句
        if (onHandMessage != null) {
            onHandMessage.onHandMessage(message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

    public void setOnHandMessage(OnHandMessage onHandMessage) {
        this.onHandMessage = onHandMessage;
    }

    /**
     * 收到消息的回调接口
     */
    public interface OnHandMessage {
        void onHandMessage(String message);
    }
}
