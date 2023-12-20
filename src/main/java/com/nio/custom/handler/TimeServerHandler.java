package com.nio.custom.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        var time = ctx.alloc().buffer();
        time.writeBytes(LocalDateTime.now().toString().getBytes());
        var channelFuture = ctx.channel().writeAndFlush(time);
//        channelFuture.addListener((ChannelFutureListener) future -> ctx.close());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
