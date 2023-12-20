package com.nio.custom.handler;

import com.nio.custom.message.ReceiveC2cMessage;
import com.nio.custom.message.SendC2cMessage;
import com.nio.custom.service.Session;
import com.nio.custom.service.SessionFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class C2cServerHandler extends SimpleChannelInboundHandler<SendC2cMessage> {
    private Session session = SessionFactory.getSession();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendC2cMessage msg) throws Exception {
        var from = msg.getFrom();
        var to = msg.getTo();
        var toChannel = session.getChannel(to);
        if (toChannel == null) {
//            session.getChannel(from).writeAndFlush("对方不在线");
            System.out.println("对方不在线");
            return;
        }
        toChannel.writeAndFlush(new ReceiveC2cMessage(from, to, msg.getContent()));

    }
}
