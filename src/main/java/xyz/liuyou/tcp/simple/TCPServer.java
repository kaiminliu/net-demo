package xyz.liuyou.tcp.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 18:52
 * @decription
 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 1、创建服务端套接字对象 参数：服务端监听端口
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("server running...");
        // 2、监听
        Socket client = serverSocket.accept();
        // 3、获取输入流
        InputStream is = client.getInputStream();
        // 4、获取字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 5、读取消息
        int len;
        byte[] buffer = new byte[1024];
        while((len=is.read(buffer))!=-1){
            baos.write(buffer, 0, len);
        }
        // 打印消息
        System.out.println(baos.toString());
        // 或者 必须保证 读取的字节小于buffer容量，不然数据部完整
        // System.out.println(new String(buffer));
        // 6、关闭流
        baos.close();
        is.close();
        client.close();
        serverSocket.close();
    }
}
