package com.leaf.redis.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

public class TestSocketServer {
    private final static String SOAP_BEGIN = "heart";
    private final static String SOAP_END = "beat";
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestSocketServer testserver=new TestSocketServer();
        testserver.start();
    }
    public void start(){
        try{
            ServerSocket serversocket=new ServerSocket(18889);
            while(true){
                Socket socket=serversocket.accept();
                new SocketThread(socket).start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    class SocketThread extends Thread{
        private Socket socket;
        private String temp;
        public SocketThread(Socket socket){
            this.socket=socket;
        }
        public Socket getsocket(){
            return this.socket;
        }
        public void setsocjet(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run(){
            try{
                Reader reader=new InputStreamReader(socket.getInputStream());
                Writer writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"GBK"));
                CharBuffer charbuffer=CharBuffer.allocate(8192);
                int readindex=-1;
                char[] buffer=new char[1024];
                int index=-1;
                StringBuilder sb=new StringBuilder();
                while ((index=reader.read(buffer))!=-1){
                    System.out.println("index:"+index);
                    String result=new String(buffer,0,index);
                    System.out.println("re:"+result);
                    sb.append(reader).append(" ");
                    index=-1;
                    writer.write("receive the soap message hahahah");
                    writer.flush();
                }
                System.out.println("sb:"+sb.toString());
                writer.write("receive the soap message hahahah");
                writer.flush();

                /*while((readindex=reader.read(charbuffer))!=-1){
                    charbuffer.flip();
                    temp+=charbuffer.toString();
                    System.out.println("temp:"+temp);
                    *//*if(temp.indexOf(SOAP_BEGIN)!=-1 && temp.indexOf(SOAP_END)!=-1){
                        temp="";
                        writer.write("receive the soap message hahahah");
                        writer.flush();
                    }else if(temp.indexOf(SOAP_BEGIN)!=-1){
                        temp=temp.substring(temp.indexOf(SOAP_BEGIN));
                    }
                    if(temp.length()>1024*16){
                        break;
                    }*//*
                }*/
                System.out.println(temp);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(socket!=null){
                    try{
                        if(!socket.isClosed()){
                            socket.close();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
