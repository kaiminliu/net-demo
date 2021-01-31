package xyz.liuyou.udp.runnable_talk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/31 6:56
 * @decription
 **/
public class TalkReceive implements Runnable{

    /**
     * 接收数据包套接字
     */
    private DatagramSocket receiver;
    /**
     * 消息发送方名字
     * (可以包含着发送方的数据里，即可以不需要这个字段)
     */
    private String messageFrom;
    /**
     * 监听的端口
     */
    private int listenerPort;
    /**
     * 容器大小（不能太小，否则乱码）
     */
    private static final int CONTAINER_SIZE = 1024;
    /**
     * 接收方名字
     */
    private String receiverName;

    public TalkReceive(String receiverName, String messageFrom, int listenerPort){
        this.receiverName = receiverName;
        this.messageFrom = messageFrom;
        this.listenerPort = listenerPort;
        try {
            // 1、创建套接字 监听端口
            receiver = new DatagramSocket(listenerPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(receiverName + " receiver running...");
        receive();
        System.out.println("对方关闭连接，" + receiverName + " receiver closed!!!");
        System.out.print(receiverName + ">");
    }

    private void receive(){
        try {
            while (true){
                // 2、创建缓存
                byte[] container = new byte[CONTAINER_SIZE];
                // 3、创建数据包对象
                DatagramPacket packet = new DatagramPacket(container, 0, container.length);
                // 4、接收数据包（synchronized 同步阻塞）
                receiver.receive(packet);
                // 5、获取数据包里的数据
                byte[] data = packet.getData();
                String message = new String(data, 0, data.length).trim();
                // 6、打印数据
                System.out.println(messageFrom + "("+ listenerPort +") : " + message);
                System.out.print(receiverName + ">");
                if ("bye".equals(message.trim())){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 7、关闭资源
        if (receiver != null) {
            receiver.close();
        }
    }
}
