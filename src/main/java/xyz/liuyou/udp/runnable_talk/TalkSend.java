package xyz.liuyou.udp.runnable_talk;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/31 6:56
 * @decription
 **/
public class TalkSend implements Runnable {

    /**
     *  发送方数据包套接字
     */
    private DatagramSocket sender;
    /**
     * 接收方ip
     */
    private String toIp;
    /**
     * 接收方port
     */
    private int toPort;
    /**
     * 发送方名字
     */
    private String senderName;
    /**
     * 流对象（从控制台获取消息）
     */
    private BufferedReader reader;

    public TalkSend(String senderName, String toIp, int toPort){
        this.senderName = senderName;
        this.toIp = toIp;
        this.toPort = toPort;
        try {
            // 1、创建套接字
            sender = new DatagramSocket();
            // 2（1）、从控制台接收需要发送的消息
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(senderName + " sender running...");
        send();
        System.out.println(senderName + " sender closed!!!");
    }

    private void send(){
        while(true){
            try {
                // 2（2）、发送的消息
                System.out.print(senderName + ">");
                String message = reader.readLine();
                byte[] data = message.getBytes();
                // 3、创建数据包
                DatagramPacket packet = new DatagramPacket(data, 0, data.length, new InetSocketAddress(toIp,toPort));
                // 4、发送
                sender.send(packet);
                if ("bye".equals(message)){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 5、关闭资源
        if (sender != null) {
            sender.close();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
