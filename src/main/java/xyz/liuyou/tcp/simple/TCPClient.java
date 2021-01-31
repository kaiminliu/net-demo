package xyz.liuyou.tcp.simple;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 18:51
 * @decription
 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 1、创建套接字对象 参数 （预请求的服务器主机ip，预请求的服务器监听端口）
        Socket client = new Socket("127.0.0.1", 8000);
        // 2、创建输出流
        OutputStream os = client.getOutputStream();
        // 3、发送消息给服务器（写数据）
        os.write("TCPClient 发送消息给 TCPServer".getBytes());
        // 4、关闭流
        os.close();
        client.close();
    }
}
