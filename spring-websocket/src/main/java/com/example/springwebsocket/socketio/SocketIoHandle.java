package com.example.springwebsocket.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

@Component
public class SocketIoHandle {

    /**
     * 客户端连上socket服务器时执行此事件
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("SocketIoHandle 收到连接：" + client.getSessionId());
    }

    /**
     * 客户端断开socket服务器时执行此事件
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("当前链接关闭：" + client.getSessionId());
    }

    @OnEvent( value = "onMsg")
    public void onMessage(SocketIOClient client, AckRequest request, Object data) {
        System.out.println("SocketIoHandle 收到消息：" + data);
        request.isAckRequested();
        client.sendEvent("chatMsg", "我是websocket后端服务");
    }
}
