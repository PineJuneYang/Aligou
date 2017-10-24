package com.alg.ailigou.websocket;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.websocket
 * Created by Chris Chen on 2017/8/3 14:41.
 * Explain:WebSocket协议
 */

public class WebSocketProtocol {
    public int type;//消息类别 0:预留 1.做单确认 2.做单结果 3.内部广播
    public int sendId;//发送者ID
    public List<Integer> acceptIds;//接收者ID集合
    public boolean isBroadCast;//是否广播
    public int format;//消息格式:string/html/emote/audio/video/image/file
    public double lng;//经度
    public double lat;//纬度
}
