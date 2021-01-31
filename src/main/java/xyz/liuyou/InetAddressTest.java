package xyz.liuyou;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author liuminkai
 * @version 1.0
 * @decription InetAddress类测试
 **/
public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        /**
         * 获取ip地址对象
         */
        // 获取本地主机ip地址对象
        InetAddress localIpAddress = InetAddress.getLocalHost();
        System.out.println(localIpAddress);// LAPTOP-CH9MP0KB/192.168.137.1
        // 通过主机ip地址或域名，ip获取ip地址对象
        InetAddress byName = InetAddress.getByName("127.0.0.1");
        System.out.println(byName);// /127.0.0.1
        // 通过主机名获取多个ip地址对象
        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        Arrays.asList(allByName).stream().forEach(System.out::println); // www.baidu.com/182.61.200.6     www.baidu.com/182.61.200.7
        // 获取回环ip地址对象
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress); // localhost/127.0.0.1

        /**
         * 常用方法
         **/
        byte[] address = localIpAddress.getAddress();
        System.out.println(address);// [B@6d311334
        String canonicalHostName = localIpAddress.getCanonicalHostName(); // 规范主机名
        System.out.println(canonicalHostName);// LAPTOP-CH9MP0KB.mshome.net
        String hostAddress = localIpAddress.getHostAddress(); // 主机ip地址
        System.out.println(hostAddress); // 192.168.137.1
        String hostName = localIpAddress.getHostName();// 主机名
        System.out.println(hostName);// LAPTOP-CH9MP0KB
        boolean reachable = localIpAddress.isReachable(3000);// 3s内是否可到达
        System.out.println(reachable);// true
        // ...
    }
}
