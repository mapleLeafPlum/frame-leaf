package com.leaf.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class RactorServer {
    public static void main(String[] args) throws Exception{
        RactorServer servers=new RactorServer();
        servers.start();
    }

    public void start() throws Exception{
        new Thread(new SelectClietn()).start();
    }

    class SelectClietn implements Runnable{
        private Selector selector;

        public SelectClietn(){
            init();
        }

        public void init(){
            try {
                System.out.println("init.....");
                selector=Selector.open();
                ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(8846));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
                System.out.println("init.over.....");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                System.out.println("run.....");
                while(true){
                    //获取就绪channel
                    int count = selector.select(2000);
                    System.out.println("count....."+count);
                    if(count>0){
                        Set<SelectionKey> keys=selector.selectedKeys();
                        Iterator<SelectionKey> iterator=keys.iterator();
                        while (iterator.hasNext()){
                            System.out.println("parsing....."+count);
                            SelectionKey key= iterator.next();
                            if(key.isValid()) {
                                //若此key的通道是等待接受新的套接字连接
                                if (key.isAcceptable()) {
                                    iterator.remove();
                                    System.out.println(key.toString() + " : 接收");
                                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                                    //接受socket
                                    SocketChannel channel = serverSocketChannel.accept();
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    int size = channel.read(buffer);
                                    System.out.println("size:"+size);
                                    if (size > 0) {
                                        buffer.flip();
                                        byte[] out = new byte[buffer.remaining()];
                                        buffer.get(out);
                                        System.out.println("client-message:" + new String(out));
                                    }

                                    buffer.clear();
                                    channel.write(ByteBuffer.wrap("yes nici meet you".getBytes()));

                                    channel.close();
                                    //selector.selectedKeys().remove(key);

                                   /* channel.configureBlocking(false);
                                    //将channel加入到selector中，并一开始读取数据
                                    channel.register(selector, SelectionKey.OP_READ);*/
                                    //若此key的通道是有数据可读状态
                                } else if (key.isValid() &&key.isReadable()) {
                                    System.out.println(key.toString() + " : 读");
                                    SocketChannel socket = (SocketChannel) key.channel();
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    int size = socket.read(buffer);
                                    System.out.println("size:"+size);
                                    if (size > 0) {
                                        buffer.flip();
                                        byte[] out = new byte[buffer.remaining()];
                                        buffer.get(out);
                                        System.out.println("client-message:" + new String(out));
                                    }
                                    buffer.clear();
                                    socket.write(ByteBuffer.wrap("yes nici meet you".getBytes()));
                                    //若此key的通道是写数据状态
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
