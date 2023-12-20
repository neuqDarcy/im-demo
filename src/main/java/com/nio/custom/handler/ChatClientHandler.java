package com.nio.custom.handler;

import com.nio.custom.message.OnlineMessage;
import com.nio.custom.message.SendC2cMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

public class ChatClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        new Thread(() -> {
            String userName = "";
            System.out.println("==================================");
            System.out.println("login [username]");
            System.out.println("send [username] [content]");
//        System.out.println("gsend [group name] [content]");
//        System.out.println("gcreate [group name] [m1,m2,m3...]");
//        System.out.println("gmembers [group name]");
//        System.out.println("gjoin [group name]");
//        System.out.println("gquit [group name]");
            System.out.println("quit");
            System.out.println("==================================");
            while (true) {
                var scanner = new Scanner(System.in);
                var command = scanner.nextLine().split(" ");
                switch (command[0]) {
                    case "login":
                        userName = command[1];
                        var onlineReq = new OnlineMessage(userName);
                        ctx.channel().writeAndFlush(onlineReq);
                        break;
                    case "send":
                        var to = command[1];
                        var content = command[2];
                        var sendC2cMsgReq = new SendC2cMessage(userName, to, content);
                        ctx.channel().writeAndFlush(sendC2cMsgReq);
                        break;
                    case "quit":
                        System.out.println("quit..........");
                        ctx.close();
                        return;
                }
            }
        }).start();
    }
}

