package com.leaf.socket.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import javax.swing.*;

public class DiscardServerHandler  extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        System.out.println("channelRead....");
        if(msg instanceof ByteBuf){
            ByteBuf in = (ByteBuf) msg;

            byte[] buff=new byte[in.readableBytes()];
            in.readBytes(buff);
            System.out.println(new String(buff));
            ByteBuf resp= Unpooled.copiedBuffer("fuck you aaah aah".getBytes());
            ctx.writeAndFlush(resp);
        }else if(msg instanceof  String){
            System.out.println("result:"+msg);
            ByteBuf resp= Unpooled.copiedBuffer("fuck you aaah aah".getBytes());
            ctx.writeAndFlush(resp);
        }
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
       // super.channelRegistered(ctx);
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
       // super.channelUnregistered(ctx);
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // super.channelActive(ctx);
        System.out.println("channelActive");
        ctx.channel().writeAndFlush("fuck");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       // super.channelInactive(ctx);
        System.out.println("channelInactive");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        System.out.println("channelReadComplete");
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //super.userEventTriggered(ctx, evt);
        System.out.println("userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
       // super.channelWritabilityChanged(ctx);
        System.out.println("channelWritabilityChanged");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }


}
