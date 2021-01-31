package xyz.liuyou.tcp.fileupload;

import java.io.*;
import java.net.Socket;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 18:52
 * @decription
 **/
public class FileTCPClient {
    public static void main(String[] args) throws IOException {
        // 1、获取套接字对象
        Socket client = new Socket("127.0.0.1", 8080);
        // 2、创建文件输入流
        FileInputStream fis = new FileInputStream( "C:\\Users\\14239\\Desktop\\流柚的小仓库\\项目及项目经验\\Demo\\net-demo\\logo.png");
        BufferedInputStream bis = new BufferedInputStream(fis);
        // 3、获取输出流
        OutputStream os = client.getOutputStream();
        // 4、将文件写出
        int len;
        byte[] buffer = new byte[1024];
        while((len=bis.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }
        // 5、通知服务器，我已经传输完毕，你快点把文件存起来，返回消息给我
        client.shutdownOutput(); // 避免阻塞
        // 6、获取输入流
        InputStream is = client.getInputStream(); // 阻塞
        // 7、读取服务端信息
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((len=is.read(buffer))!=-1){
            baos.write(buffer, 0, len);
        }
        // 8、打印信息
        System.out.println(baos.toString());
        // 5、关闭流
        baos.close();
        is.close();
        os.close();
        bis.close();
        fis.close();
        client.close();

    }
}
