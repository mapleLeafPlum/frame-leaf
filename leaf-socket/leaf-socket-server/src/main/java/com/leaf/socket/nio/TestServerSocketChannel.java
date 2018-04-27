package com.leaf.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServerSocketChannel {
    public static void main(String[] args) throws Exception{
        TestServerSocketChannel servers=new TestServerSocketChannel();
        servers.start();
    }

    public void start() throws Exception{

        ServerSocketChannel server=ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress("192.168.1.42",8021));
        server.configureBlocking(false);
        while(true){
            SocketChannel channel=server.accept();
            if(channel!=null){
                channel.configureBlocking(false);
                new Thread(new ServerNio(channel)).start();
            }
        }
    }

    class ServerNio implements Runnable{
        private SocketChannel socket;
        public ServerNio(SocketChannel socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            try {
                ByteBuffer buffer=ByteBuffer.allocate(1024);
                int count=socket.read(buffer);
                if(count>0){
                    buffer.flip();
                    byte[] out=new byte[buffer.remaining()];
                    buffer.get(out);
                    System.out.println("connect message:"+new String(out));
                }
                socket.write(ByteBuffer.wrap(Thread.currentThread().getName().getBytes()));
                buffer.clear();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
