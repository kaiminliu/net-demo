package xyz.liuyou.url;

import java.io.*;
import java.net.URL;

/**
 * @author liuminkai
 * @version 1.0
 * @decription 使用url下载网络资源
 **/
public class URLDownload {
    public static void main(String[] args) throws IOException {
        // 创建资源url对象
        URL url = new URL("https://cdn.jsdelivr.net/gh/liuminkai-blog/liuminkai-blog.github.io//medias/logo.png");
        // 生成输入流
        InputStream is = url.openStream();
        // 创建文件输出流，在项目根目录下，文件名为logo.png
        FileOutputStream fos = new FileOutputStream("logo.png");
        // 读取并写入到文件中
        int len;
        byte[] buffer = new byte[1024];
        while ((len=is.read(buffer)) != -1) {
            fos.write(buffer, 0 , len);
        }
        // 关闭流
        fos.close();
        is.close();
    }
}
