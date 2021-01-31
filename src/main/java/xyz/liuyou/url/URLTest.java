package xyz.liuyou.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author liuminkai
 * @version 1.0
 * @decription URL测试
 **/
public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://baike.baidu.com/item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%B3%BB%E7%BB%9F?fromtitle=url&fromid=110640");

        int port = url.getPort();// 端口
        System.out.println(port); // -1
        String authority = url.getAuthority();//
        System.out.println(authority);// baike.baidu.com
        Object content = url.getContent();// 内容
        System.out.println(content);// sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@97e1986
        int defaultPort = url.getDefaultPort();// 默认端口
        System.out.println(defaultPort);// 443
        String userInfo = url.getUserInfo();// 用户信息
        System.out.println(userInfo);// null
        String host = url.getHost();// 主机名
        System.out.println(host);// baike.baidu.com
        String path = url.getPath();// 路径
        System.out.println(path);// /item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%B3%BB%E7%BB%9F
        String protocol = url.getProtocol();// 协议
        System.out.println(protocol);// https
        String query = url.getQuery();// 查询字符串
        System.out.println(query);// fromtitle=url&fromid=110640
        String ref = url.getRef();
        System.out.println(ref); // null
        String file = url.getFile();// 路径 + 查询字符串
        System.out.println(file);// /item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%B3%BB%E7%BB%9F?fromtitle=url&fromid=110640
    }
}
