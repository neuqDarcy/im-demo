package com.nio.custom.handler;

import com.nio.custom.message.ReceiveC2cMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class C2cClientHandler extends SimpleChannelInboundHandler<ReceiveC2cMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReceiveC2cMessage msg) throws Exception {
        System.out.println(msg.getFrom() + ": " + msg.getContent());
    }
}
