package com.springretry;



import static com.springretry.WeTalkUtils.recvMsg;
import static com.springretry.WeTalkUtils.sendMsg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * WeTalk 客户端
 */
public class WeTalkClient {

    private static final String EXIT_MARK = "exit";

    private String hostname;

    private int port;

    WeTalkClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() throws IOException {
        // 打开一个套接字通道，并向服务端发起连接
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(hostname, port));

        Scanner sc = new Scanner(System.in);
        while (true) {
            // 输入信息
            System.out.println("请输入：");
            String msg = sc.nextLine();
            if (EXIT_MARK.equals(msg)) {
                sendMsg(channel, "bye~");
                break;
            }
            
            // 向服务端发送消息
            sendMsg(channel, msg);
            
            // 接受服务端返回的消息
            msg = recvMsg(channel);
            System.out.println("\n服务端：");
            System.out.println(msg + "\n");
        }
        // 关闭通道
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        new WeTalkClient("10.206.84.188", 8080).start();
    }
}
