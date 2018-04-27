package com.leaf.spring.socket;

import com.google.gson.Gson;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ServerEndpoint("/echo")
public class EchoSocket {

    private String userName;
    private static List<Session> sessionList=new ArrayList<>();
    private static List<String> userList=new ArrayList<>();
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Gson gson=new Gson();
    public EchoSocket(){
        System.out.println("EchoSocket"+this.getClass());
    }

    @OnOpen
    public void open(Session session){
        String user=session.getQueryString();
        System.out.println("onopen");
        System.out.println(session.getId());
        sessionList.add(session);
        userList.add(user.split("=")[1]);
        userName=user.split("=")[1];
        String message="热烈欢迎："+userName+"登录聊天系统";
        Message me=new Message();
        me.setMessage(message);
        me.setUserList(this.userList);
        System.out.println(gson.toJson(me));
        broadcarstMessage(gson.toJson(me));
    }

    @OnMessage
    public void message(Session session,String msg){
        System.out.println("message:"+msg);
        System.out.println(session.getId());
       /* try {
            session.getBasicRemote().sendText("fuck you to");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String message=sdf.format(new Date())+" "+userName+"："+msg;
        Message me=new Message();
        me.setMessage(message);
        me.setUserList(this.userList);
        broadcarstMessage(gson.toJson(me));
    }

    private void broadcarstMessage(String message){
        for (Session item:sessionList){
            try {
                item.getBasicRemote().sendText(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnMessage
    public void messageBinary(Session session,ByteBuffer buffer){
        System.out.println("messageBinary:");
        System.out.println( Charset.defaultCharset().decode(buffer).toString());
        System.out.println(session.getId());
    }

    @OnClose
    public void close(Session session){
        sessionList.remove(session);
        userList.remove(this.userName);
        String message=sdf.format(new Date())+" "+userName+"退出聊天室";
        Message me=new Message();
        me.setMessage(message);
        me.setUserList(this.userList);

        broadcarstMessage(gson.toJson(me));
        System.out.println("close:");
    }

}
