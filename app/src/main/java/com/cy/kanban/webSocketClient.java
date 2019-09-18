package com.cy.kanban;


import android.content.Context;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class webSocketClient extends WebSocketClient {
    private Context mContext;
    //选择懒汉模式
    private static webSocketClient mInstance;

    //1. 私有构造方法
    private webSocketClient(Context context) {
        //开启WebSocket客户端
        super(URI.create("webSocket链接"));
        this.mContext = context;
    }

    //2.公开方法,返回单例对象
    public static webSocketClient getInstance(Context context) {
        //懒汉: 考虑线程安全问题, 两种方式: 1. 给方法加同步锁 synchronized, 效率低; 2. 给创建对象的代码块加同步锁
        if (mInstance == null) {
            synchronized (webSocketClient.class) {
                if (mInstance == null) {
                    mInstance = new webSocketClient(context);
                }
            }
        }
        return mInstance;
    }

    public webSocketClient(URI serverUri) {
        super(serverUri);
    }

    //长链接开启
    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    //消息通道收到消息
    @Override
    public void onMessage(String message) {
    }

    //长链接关闭
    @Override
    public void onClose(int code, String reason, boolean remote) {
    }

    //链接发生错误
    @Override
    public void onError(Exception ex) {
    }
}