package com.nio.custom;

import com.nio.custom.handler.C2cServerHandler;
import com.nio.custom.handler.MessageCodecSharable;
import com.nio.custom.handler.OnlineServerhandler;
import com.nio.custom.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    public static void main(String[] args) {
        try {
            var channel = new ServerBootstrap()
                    .group(new NioEventLoopGroup())
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new ProtocolFrameDecoder())
                                    .addLast(new MessageCodecSharable())
                                    //                                .addLast(new EchoServerHandler());
                                    //                                    .addLast(new TimeServerHandler());
                                    .addLast(new OnlineServerhandler())
                                    .addLast(new C2cServerHandler());
                        }
                    }).bind(8888).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}