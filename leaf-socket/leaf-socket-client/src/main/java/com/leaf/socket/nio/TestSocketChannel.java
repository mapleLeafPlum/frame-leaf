package com.leaf.socket.nio;


import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestSocketChannel {
    public static void main(String[] args) {
        TestSocketChannel socketChannel=new TestSocketChannel();
        socketChannel.connect();
    }

    public void connect(){
        for (int i=0;i<1000;i++){
            new Thread(new Clinet()).start();
        }
    }

   public class Clinet implements Runnable{

        @Override
        public void run() {
            try {
                SocketChannel socketChannel=SocketChannel.open();
                //socketChannel.configureBlocking(false);
                boolean ok=socketChannel.connect(new InetSocketAddress("192.168.1.42",9999));
                System.out.println(ok);
                if(ok){
                    socketChannel.write(ByteBuffer.wrap(Thread.currentThread().getName().getBytes()));
                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    int count=socketChannel.read(buffer);
                    if(count>0){
                        buffer.flip();
                        byte[] out=new byte[buffer.remaining()];
                        buffer.get(out);
                        System.out.println("server-message:"+new String(out));
                    }
                    socketChannel.close();
                }else{
                    System.out.println(ok);
                }
            }catch (Exception e){
               e.printStackTrace();
            }
        }
    }
}
