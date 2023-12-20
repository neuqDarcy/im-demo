package com.nio.custom.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendC2cMessage extends Message{
    private String from;
    private String to;
    private String content;

    @Override
    public int getMessageType() {
        return SEND_C2C_MESSAGE;
    }
}
