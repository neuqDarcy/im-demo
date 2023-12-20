package com.nio.custom.protocol;

import java.io.IOException;

public interface Serializer {
    <T> T deserializer(Class<T> clazz, byte[] bytes);
    <T> byte[] serialize(T object) throws IOException;
}
