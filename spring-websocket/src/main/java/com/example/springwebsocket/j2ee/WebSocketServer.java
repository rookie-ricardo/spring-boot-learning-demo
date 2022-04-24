package com.example.springwebsocket.j2ee;

import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint("/j2ee-ws/{msg}")
public class WebSocketServer {
    public WebSocketServer() {
        System.out.println("初始化WebSocketServer");
    }

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "msg") String msg){
        System.out.println("WebSocketServer 收到连接: " + session.getId() + ", 当前消息：" + msg);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        message = "WebSocketServer 收到连接：" + session.getId() +  "，已收到消息：" + message;
        System.out.println(message);
        session.getBasicRemote().sendText(message);
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){

        System.out.println("发生错误");
        throwable.printStackTrace();
    }



}
