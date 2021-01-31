# 网络编程
## [TCP/IP](https://baike.baidu.com/item/tcp/ip%E5%8D%8F%E8%AE%AE%E7%B0%87/1137204)
TCP/IP协议簇是Internet的基础，也是当今最流行的组网形式。
主要包含的协议：IP协议、ARP协议、TCP协议、UDP协议、FTP协议。。。
### 传输层协议
传输层协议分为TCP、UDP
#### TCP-传输控制协议（Transmission Control Protocol）
- 面向连接的、可靠的、基于字节流的传输层通信协议
- 数据大小无限制
- 连接三次握手，断开连接四次握手

#### UDP协议-用户数据报协议（User Datagram Protocol）
- 是一种无连接的传输层协议
- 提供面向事务的简单不可靠信息传送服务，每个包的大小64KB

## IP地址
互联网协议地址，是给因特网上的每台计算机和其它设备都规定了一个唯一的地址
### 分类
- 版本
    - [IPv4](https://baike.baidu.com/item/IPv4) （42亿，不够用）：2019年11月26日，全球所有43亿个IPv4地址已分配完毕
        - 占4字节（32位）
        - 常用表示方式：点分十进制，如 255.255.255.255
    - [IPv6](https://baike.baidu.com/item/IPv6)
        - 占16字节（128位）
        - 常用表示方式：
            - 冒分十六进制，如 ABCD:EF01:2345:6789:ABCD:EF01:2345:6789
            - 0位压缩表示法，连续的0压缩为“::”，如  0:0:0:0:0:0:0:1 → ::1
            - 内嵌IPv4地址表示法，如 X:X:X:X:X:X:d.d.d.d
- 类型
    - 公网（互联网）
        - 网段：除了私网网段
    - 私网（局域网）
        - 网段
            - A：10.0.0.0/8：10.0.0.0-10.255.255.255
            - B：172.16.0.0/12：172.16.0.0-172.31.255.255
            - C：192.168.0.0/16：192.168.0.0-192.168.255.255

### IPv6编址方式
- 网络号+主机号
    - A类
    - B类
    - C类
    - D类（组播）
    - E类（保留）
- 特殊网址
    - 0.0.0.0
        - 0.0.0.0/8 本网络的所有主机
        - 0.0.0.0/32 本机的源地址
    - 255.255.255.255 广播地址
    - 127.0.0.1-127.255.255.255 回路测试
        - 127.0.0.1 本机ip地址（测试） 

- 子网
    - 子网划分（通过子网掩码将大网划分为多个小网）
- 超网
    - 构造超网（通过子网的最长网络前缀构造超网）
- 无类别域间路由（CIDR）：
    - 消除**传统**A类、B类、..、及划分子网的概念，将所有网络结合，更加有效地分配IPv4的地址空间
    - 常用表示表示方式：斜线记法，如 192.168.111.88/25 对应C类地址，前25位网络号，后7位主机号
    - VLSM（可变长子网掩码）
     

## 端口号（0~65535）
不同的进程有不同的端口号，端口号不可冲突！用来区分软件
- 公有端口：0~1023
    - HTTP：80
    - HTTPS：443
    - FTP：21
    - Telent: 23 
    - ...
- 注册端口：1024~49151
    - Tomcat：8080
    - MySQL：3306
    - Oracle：1521
    - ...
- 动态、私有端口：49152~65535


## 网络编程：InetAddress
```java
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
```

## 网络编程：InetSocketAddress
套接字：<ip地址:端口>
public class InetSocketAddressTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("xx", 80);
        
        InetAddress address = inetSocketAddress.getAddress();// ip地址对象
        System.out.println(address);// localhost/127.0.0.1
        String hostName = inetSocketAddress.getHostName();// 主机名
        System.out.println(hostName);// localhost
        String hostString = inetSocketAddress.getHostString();// 主机字符串
        int port = inetSocketAddress.getPort();// 端口
        System.out.println(port);// 80
        boolean unresolved = inetSocketAddress.isUnresolved();// 主机名是否不能被解析为ip地址对象
        System.out.println(unresolved);// true （不能解析）
    }
}