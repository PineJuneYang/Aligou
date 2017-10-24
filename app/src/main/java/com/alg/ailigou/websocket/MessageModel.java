package com.alg.ailigou.websocket;

/**
 * AiligouApp
 * com.alg.ailigou.websocket
 * Created by Chris Chen on 2017/8/3 14:41.
 * Explain:长连接消息模型
 */

public class MessageModel<DataModel> {
    public WebSocketProtocol protocol;//协议
    public String content;//消息内容
    public DataModel data;//数据
}
