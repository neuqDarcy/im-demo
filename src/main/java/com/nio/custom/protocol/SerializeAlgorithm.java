package com.nio.custom.protocol;

import java.io.*;

public enum SerializeAlgorithm implements Serializer {
    Java {
        @Override
        public <T> T deserializer(Class<T> clazz, byte[] bytes) {
            try {
                var bis = new ByteArrayInputStream(bytes);
                var ois = new ObjectInputStream(bis);
                return (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("反序列化失败", e);
            }
        }

        @Override
        public <T> byte[] serialize(T object) {
            try {
                var bos = new ByteArrayOutputStream();
                var oos = new ObjectOutputStream(bos);
                oos.writeObject(object);
                return bos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("序列化失败", e);
            }
        }
    }


}
