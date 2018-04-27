package com.leaf.spring.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClinet {

    private Socket socket;
    private OutputStream os;
    private InputStream in;

    public static void main(String[] args) {
        SocketClinet clinet=new SocketClinet();
        clinet.onStart();
    }

    public void onStart() {
        try {
            System.out.println("客户端启动成功....");
            new Thread(new HeartBeat()).start();
          //  new Thread(new Client()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private class HeartBeat implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    //5秒发送一次心跳
                    Thread.sleep(2000);
                    try {
                        System.out.println("close:"+socket.isClosed());
                        System.out.println("connect:"+socket.isConnected());
                        System.out.println("bound:"+socket.isBound());
                    }catch (Exception e){

                    }

                    if (socket == null || socket.isClosed()) {
                        System.out.println("心跳包连接重新建立");
                        socket = new Socket("192.168.1.103", 9859);
                        os=socket.getOutputStream();
                    }else{
                        System.out.println("心跳包连接已经建立");
                    }
                    System.out.println("开始发送心跳包。。。");
                    os.write("heartbeat".getBytes());
                    os.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

   private class Client implements Runnable {
        BufferedReader reader;
        InputStream is;

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                while (true) {
                    //休眠4秒
                    Thread.sleep(4000);
                    int size = 0;
                    if (socket == null || socket.isConnected()) {
                       // System.out.println("数据包重新建立");
                        socket = new Socket("192.168.1.103", 9859);
                        os = socket.getOutputStream();
                        os.write("我来连接你了。。。".getBytes());
                    }else{
                       // System.out.println("数据包已经建立");
                    }
                    is = socket.getInputStream();
                    size = is.available();
                    if (size <= 0) {
                        if ((System.currentTimeMillis() - startTime) > 10 * 1000) { // 如果超过30秒没有收到服务器发回来的信息，说明socket连接可能已经被远程服务器关闭
                            socket.close(); // 这时候可以关闭socket连接
                            startTime = System.currentTimeMillis();
                        }
                        continue;
                    } else {
                        startTime = System.currentTimeMillis();
                    }
                    byte[] resp = new byte[size];
                    is.read(resp);
                    String response = new String(resp, "utf-8");
                    System.out.println("读取数据了："+response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(socket!=null){
                        socket.close();
                    }
                   if(is!=null){
                       is.close();
                   }
                   if(os!=null){
                       os.close();
                   }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
