package com.nio.custom;

import com.nio.custom.handler.C2cClientHandler;
import com.nio.custom.handler.ChatClientHandler;
import com.nio.custom.handler.MessageCodecSharable;
import com.nio.custom.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtocolFrameDecoder());
                        ch.pipeline().addLast(new MessageCodecSharable());
                        ch.pipeline().addLast(new ChatClientHandler());
                        ch.pipeline().addLast(new C2cClientHandler());
                    }
                })
                .connect("127.0.0.1", 8888).sync().channel();

    }
}
