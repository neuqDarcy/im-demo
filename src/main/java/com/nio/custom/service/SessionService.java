package com.nio.custom.service;

import io.netty.channel.Channel;

import java.util.HashMap;

public class SessionService implements Session {
    private HashMap<String, Channel> userChannelMap = new HashMap<>();
    private HashMap<Channel, String> channelUserMap = new HashMap<>();

    @Override
    public void bind(Channel channel, String userName) {
        userChannelMap.put(userName, channel);
        channelUserMap.put(channel, userName);
    }

    @Override
    public void unbind(Channel channel) {
        var userName = channelUserMap.get(channel);
        channelUserMap.remove(userName);
        channelUserMap.remove(channel);
    }

    @Override
    public Channel getChannel(String userName) {
        return userChannelMap.get(userName);
    }
}
