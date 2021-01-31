package xyz.liuyou;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author liuminkai
 * @version 1.0
 * @decription InetSocketAddress测试
 **/
public class InetSocketAddressTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("xx", 80);

        InetAddress address = inetSocketAddress.getAddress();// ip地址对象
        System.out.println(address);// null
        String hostName = inetSocketAddress.getHostName();// 主机名
        System.out.println(hostName);// xx
        String hostString = inetSocketAddress.getHostString();// 主机字符串
        System.out.println(hostString);// xx
        int port = inetSocketAddress.getPort();// 端口
        System.out.println(port);// 80
        boolean unresolved = inetSocketAddress.isUnresolved();// 主机名是否不能被解析为ip地址对象
        System.out.println(unresolved);// true （不能解析）
    }
}
