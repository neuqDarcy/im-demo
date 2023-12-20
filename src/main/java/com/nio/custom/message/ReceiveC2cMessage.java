package com.nio.custom.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiveC2cMessage extends Message{

    private String from;
    private String to;
    private String content;
    @Override
    public int getMessageType() {
        return RECEIVE_C2C_MESSAGE;
    }
}
