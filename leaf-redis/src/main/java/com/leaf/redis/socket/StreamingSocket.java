package com.leaf.redis.socket;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StreamingSocket {
    public static void main(String[] args) throws  Exception{

        ServerSocket server=new ServerSocket(9999);
        Executor executor = Executors.newFixedThreadPool(4);

        System.out.println("开始监听。。。。。");
        while (true){
           final Socket conn = server.accept();
           // executor.execute( new Runnable() {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        //与客户端建立连接
                        PrintWriter writer = new PrintWriter(conn.getOutputStream());
                        System.out.println("启动线程");
                        Random r = new java.util.Random();
                        //向客户端输出
                        writer.write(Thread.currentThread().getName());
                        writer.flush();
                        System.out.println("Server:" + Thread.currentThread().getName());
                        writer.close();
                    } catch (Exception tb) {
                        tb.printStackTrace();
                    }finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (Exception e) {
                                System.out.println("服务端 finally 异常:" + e.getMessage());
                            }
                        }
                    }
                }
            });
        }
       // conn.close();
    }
}
