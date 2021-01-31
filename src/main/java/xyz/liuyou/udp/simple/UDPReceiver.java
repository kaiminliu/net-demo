package xyz.liuyou.udp.simple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author liuminkai
 * @version 1.0
 **/
public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        // 1、创建套接字 监听端口8080
        DatagramSocket receiver = new DatagramSocket(8080);
        System.out.println("receiver running...");
        // 2、创建缓存
        byte[] container = new byte[1024];
        // 3、创建数据包对象
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        // 4、接收数据包（synchronized 同步阻塞）
        receiver.receive(packet);
        // 5、获取数据包里的数据
        byte[] data = packet.getData();
        // 6、打印数据
        System.out.println(new String(data));
        // 7、关闭资源
        receiver.close();
    }
}
