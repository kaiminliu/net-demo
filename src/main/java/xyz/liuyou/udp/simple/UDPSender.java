package xyz.liuyou.udp.simple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 18:54
 * @decription
 **/
public class UDPSender {
    public static void main(String[] args) throws IOException {
        // 1、创建套接字
        DatagramSocket sender = new DatagramSocket();
        System.out.println("sender running...");
        // 2、发送的消息
        byte[] message = "sender：你好！！！".getBytes();
        // 3、创建数据包
        DatagramPacket packet = new DatagramPacket(message, 0, message.length, new InetSocketAddress("127.0.0.1",8080));
        // 4、发送
        sender.send(packet);
        // 5、关闭资源
        sender.close();
    }
}
