package com.springretry;


import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 工具类
 */
public class WeTalkUtils {

    private static final int BUFFER_SIZE = 128;

    public static void sendMsg(SocketChannel channel, String msg) throws IOException {
        ByteBuffer buffer = getByteBuffer();
        buffer.put(msg.getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    public static String recvMsg(SocketChannel channel) throws IOException {
        ByteBuffer readBuffer = getByteBuffer();
        channel.read(readBuffer);
        readBuffer.flip();
        byte[] bytes = new byte[readBuffer.limit()];
        readBuffer.get(bytes);
        return new String(bytes);
    }

    public static ByteBuffer getByteBuffer() {
        return ByteBuffer.allocate(BUFFER_SIZE);
    }
}
