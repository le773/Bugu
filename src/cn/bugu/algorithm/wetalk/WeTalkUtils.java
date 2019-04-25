package cn.bugu.algorithm.wetalk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WeTalkUtils {
    private static final int BUFFER_SIZE = 128;

    public static void sendMsg(SocketChannel channel, String msg) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        byteBuffer.put(msg.getBytes()); //
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    public static String recvMsg(SocketChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        channel.read(byteBuffer);
        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);

        return new String(bytes);
    }
}
