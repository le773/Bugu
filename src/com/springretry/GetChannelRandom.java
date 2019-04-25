package com.springretry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 获取通道
 *
 * @author zhq
 *
 */
public class GetChannelRandom {
    private static final int SIZE = 1024;
    private static final String FILE_PATH = "RandomAccessFile.txt";

    public static void main(String[] args) throws Exception {
// 获取管道
        RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw");
        FileChannel rafChannel = raf.getChannel();

// 准备数据
        String data = "新数据，时间： " + System.currentTimeMillis();
        System.out.println("原数据：\n" + "   " + data);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();

// 写入数据
        rafChannel.write(buffer);
        rafChannel.close();
        raf.close();

// 重新打开管道
        raf = new RandomAccessFile(FILE_PATH, "rw");
        rafChannel = raf.getChannel();

// 读取刚刚写入的数据
        buffer.clear();
        rafChannel.read(buffer);

// 打印读取出的数据
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println("读取到的数据：\n" + "   " + new String(bytes));

        rafChannel.close();
        raf.close();
    }
    public static void transfer()  throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        // 将 fromFile 文件找那个的数据转移到 toFile 中去
        System.out.println("before transfer: " + readChannel(toChannel));
        fromChannel.transferTo(position, count, toChannel);
        System.out.println("after transfer : " + readChannel(toChannel));

        fromChannel.close();
        fromFile.close();
        toChannel.close();
        toFile.close();
    }

    private static String readChannel(FileChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.clear();

        // 将 channel 读取位置设为 0，也就是文件开始位置
        channel.position(0);
        channel.read(buffer);

        // 再次将文件位置归零
        channel.position(0);

        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return new String(bytes);
    }
}
