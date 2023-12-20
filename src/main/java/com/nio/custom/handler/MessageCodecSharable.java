package com.nio.custom.handler;

import com.nio.custom.message.Message;
import com.nio.custom.protocol.SerializeAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
        var out = channelHandlerContext.alloc().buffer();
        //1. 魔数 4 字节
        out.writeBytes(new byte[]{1, 0, 2, 4});
        //2. 版本号 1 字节
        out.writeByte(1);
        //3. 序列化算法 1字节
        out.writeByte(0);
        //4. 消息类型 1 字节
        out.writeByte(message.getMessageType());
        //5. 消息id 4 字节
        out.writeInt(message.getSequenceId());
        out.writeByte(0xff);
        // 解析消息
        var bytes = SerializeAlgorithm.Java.serialize(message);
        //6. 长度
        out.writeInt(bytes.length);
        //7. 获取内容的字节
        out.writeBytes(bytes);
        list.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        var magicNum = byteBuf.readInt();
        var version = byteBuf.readByte();
        var serializerAlgorithm = byteBuf.readByte();
        var messageType = byteBuf.readByte();
        var sequenceId = byteBuf.readInt();
        byteBuf.readByte();
        var length = byteBuf.readInt();
        var bytes = new byte[length];
        byteBuf.readBytes(bytes, 0, length);
        Message message = SerializeAlgorithm.Java.deserializer(Message.getMessageClass(messageType), bytes);
        list.add(message);
    }
}
