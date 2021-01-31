package xyz.liuyou.tcp.fileupload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 18:53
 * @decription
 **/
public class FileTCPServer {
    public static void main(String[] args) throws IOException {
        Socket client = null;
        InputStream is = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[1024];
        // 1、创建套接字对象
        ServerSocket server = new ServerSocket(8080);
        System.out.println("server running...");
        // 2、监听 循环
        client = server.accept(); // 阻塞
        // 3、获取输入流
        is = client.getInputStream();
        // 4、文件输出流
        fos = new FileOutputStream( "C:\\Users\\14239\\Desktop\\流柚的小仓库\\项目及项目经验\\Demo\\net-demo\\new_logo.png");

        // 5、写出文件
        int len;
        while((len=is.read(buffer))!=-1){
            fos.write(buffer, 0, len);
        }
        // 6、返回消息
        OutputStream os = client.getOutputStream();
        os.write("服务端：已接收到文件".getBytes());
        // 7、关闭资源
        os.close();
        fos.close();
        is.close();
        client.close();
        server.close();
    }
}
