package com.leaf.redis.socket;

import sun.plugin2.message.HeartbeatMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
    public static void main(String[] args) {
        SocketServerTest serverTest=new SocketServerTest();
        serverTest.onStart();
    }

    public void onStart(){
        try {
            ServerSocket serverSocket=new ServerSocket(9859);
            System.out.println("服务端启动成功");
            while (true){
                Socket socket= serverSocket.accept();
                new Thread(new Server(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


   class Server implements Runnable{
       Socket socket;
       BufferedReader reader;
       BufferedWriter writer;

       public Server(Socket socket) {
           super();
           this.socket = socket;
       }
       @Override
       public void run() {
           try {
               reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//读取客户端消息
               writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//向客户端写消息
               String heatbeat=reader.readLine();
               //心跳包  不回
               if("heatbeat".equals(heatbeat)){
                   System.out.println("接受到了心跳包。。。。。");
               }else{
                   StringBuilder sb=new StringBuilder();
                   sb.append(heatbeat);

                   while((heatbeat=reader.readLine())!=null){
                       sb.append(heatbeat);
                   }
                   System.out.println("接受到了数据："+sb.toString());
                   writer.write("服务器返回结果："+sb.toString()+"\n");
                   writer.flush();
               }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               try {
                   if (reader!=null) {
                       reader.close();
                   }
                   if (writer!=null) {
                       writer.close();
                   }
                  if (socket!=null) {
                       socket.close();
                   }
               } catch (Exception e2) {
                   e2.printStackTrace();
               }
           }
       }
   }

}
