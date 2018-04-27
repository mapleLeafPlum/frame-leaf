package com.leaf.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServer {
    public static void main(String[] args) throws Exception {
        start();
    }

    public static void start() throws Exception{
        Selector selector= Selector.open();
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8846));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("init.over.....");

        while(true){
            System.out.println("loop.....");
            //获取就绪channel
            int count = selector.select(2000);
            System.out.println("count....."+count);
            if(count>0){
                System.out.println("process....."+count);
                Set<SelectionKey> keys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=keys.iterator();
                while (iterator.hasNext()){
                    System.out.println("parsing....."+count);
                    SelectionKey key= iterator.next();
                    System.out.println(key.toString() + " : handlerIo"+key.isValid());
                    if(key.isValid()) {
                        //若此key的通道是等待接受新的套接字连接
                       /* if (key.isAcceptable()) {
                            iterator.remove();
                            System.out.println(key.toString() + " : 接收");
                            ServerSocketChannel socket = (ServerSocketChannel) key.channel();
                            //接受socket
                            SocketChannel channel = socket.accept();
                            channel.configureBlocking(false);
                            //将channel加入到selector中，并一开始读取数据
                            channel.register(selector, SelectionKey.OP_READ);
                            //若此key的通道是有数据可读状态
                        } else*/ if (key.isAcceptable()) {
                            System.out.println(key.toString() + " : 读");
                            SocketChannel socket = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int size = socket.read(buffer);
                            if (size > 0) {
                                buffer.flip();
                                byte[] out = new byte[buffer.remaining()];
                                buffer.get(out);
                                System.out.println("client-message:" + new String(out));
                            }
                            buffer.clear();
                            //若此key的通道是写数据状态
                        } else if (key.isWritable()) {
                            System.out.println(key.toString() + " : 写");
                            SocketChannel socket = (SocketChannel) key.channel();
                            socket.write(ByteBuffer.wrap("yes nici meet you".getBytes()));
                        }
                    }
                }
            }
            System.out.println("end loog.....");
        }
    }
}
