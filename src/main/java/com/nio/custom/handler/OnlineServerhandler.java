package com.nio.custom.handler;

import com.nio.custom.message.OnlineMessage;
import com.nio.custom.service.Session;
import com.nio.custom.service.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class OnlineServerhandler extends SimpleChannelInboundHandler<OnlineMessage> {
    private Session session = SessionFactory.getSession();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OnlineMessage msg) throws Exception {
        session.bind(ctx.channel(), msg.getUserName());
    }
}
