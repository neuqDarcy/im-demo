package com.nio.custom.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
@Data
public abstract class Message implements Serializable {
    private int sequenceId;
    private int messageType;
    public static final int ONLINE_MESSAGE = 0;
    public static final int SEND_C2C_MESSAGE = 1;
    public static final int RECEIVE_C2C_MESSAGE = 2;
    private static final Map<Integer, Class<? extends Message>> messageClasses = Map.of(
            ONLINE_MESSAGE, OnlineMessage.class, SEND_C2C_MESSAGE, SendC2cMessage.class, RECEIVE_C2C_MESSAGE, ReceiveC2cMessage.class);
    public abstract int getMessageType();
    public static Class<? extends Message> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }
}

