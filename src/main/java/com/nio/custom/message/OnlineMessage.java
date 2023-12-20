package com.nio.custom.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class OnlineMessage extends Message {
    private String userName;

    @Override
    public int getMessageType() {
        return ONLINE_MESSAGE;
    }
}
