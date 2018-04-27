package com.leaf.redis.socket;

import java.io.*;
import java.net.Socket;

public class SocketClinet {

    public void onStart() {
        try {
            Socket socket = new Socket("localhost", 9859);

            System.out.println("客户端启动成功");
            new Thread(new Client(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class HeartBeat implements Runnable {
        private Socket socket;

        public HeartBeat(Socket socket) {
            socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //5秒发送一次心跳
                    Thread.sleep(3000);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("heartbeat".getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Client implements Runnable {

        private Socket socket;
        BufferedReader reader;
        InputStream is;
        OutputStream os;

        public Client(Socket socket) {
            socket = socket;
        }

        @Override
        public void run() {
            new Thread(new HeartBeat(socket));
            long startTime = System.currentTimeMillis();
            try {
                while (true) {
                    //休眠4秒
                    Thread.sleep(4000);
                    int size = 0;
                    if (socket == null || socket.isClosed()) {
                        socket = new Socket("localhost", 8095);
                        os = socket.getOutputStream();
                        os.write("我来连接你了。。。".getBytes());
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
