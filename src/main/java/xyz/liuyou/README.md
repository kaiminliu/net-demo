
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

```java
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
```



## 网络编程：TCP

两个类：`Socket`、`ServerSocket`

### TCP实现消息发送接收

#### 客户端

```java
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
    }
}
```

#### 服务端

```java
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
    }
}
```

**测试效果**

<span style="color:red">注意：先运行服务端程序，再运行客户端</span>

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/tcp01-1612029157607.gif)

### TCP实现文件上传

#### 客户端

```java
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
```

#### 服务端

```java
![tcp02](C:/Users/14239/Pictures/tcp02.gifpublic class FileTCPServer {
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
```

**准备好上传的文件**

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/image-20210131062738389.png)

**测试效果**

<span style="color:red">注意：先运行服务端程序，再运行客户端</span>

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/tcp02.gif)

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/image-20210131065042162.png)

## 网络编程：UDP

两个类：`DatagramSocket`、`DatagramPacket`

### UDP实现消息发送接收

#### 发送方

```java
public class UDPSender {
    public static void main(String[] args) throws IOException {
        // 1、创建套接字
        DatagramSocket sender = new DatagramSocket();
        System.out.println("sender running...");
        // 2、发送的消息
        byte[] message = "sender：你好！！！".getBytes();
        // 3、创建数据包
        DatagramPacket packet = new DatagramPacket(message, 0, message.length, new InetSocketAddress("127.0.0.1",8080));
        // 4、发送
        sender.send(packet);
        // 5、关闭资源
        sender.close();
    }
}
```

#### 接收方

```java
public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        // 1、创建套接字 监听端口8080
        DatagramSocket receiver = new DatagramSocket(8080);
        System.out.println("receiver running...");
        // 2、创建缓存
        byte[] container = new byte[1024];
        // 3、创建数据包对象
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        // 4、接收数据包（synchronized 同步阻塞）
        receiver.receive(packet);
        // 5、获取数据包里的数据
        byte[] data = packet.getData();
        // 6、打印数据
        System.out.println(new String(data));
        // 7、关闭资源
        receiver.close();
    }
}
```

**效果展示**

![udp01](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/udp01.gif)

### UDP实现多线程在线聊天

#### 发送方

##### TalkSend

```java
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
```

#### 接收方

##### TalkReceive

```java
public class TalkReceive implements Runnable{

    /**
     * 接收数据包套接字
     */
    private DatagramSocket receiver;
    /**
     * 消息发送方名字
     * (可以包含着发送方的数据里，即可以不需要这个字段)
     */
    private String messageFrom;
    /**
     * 监听的端口
     */
    private int listenerPort;
    /**
     * 容器大小（不能太小，否则乱码）
     */
    private static final int CONTAINER_SIZE = 1024;
    /**
     * 接收方名字
     */
    private String receiverName;

    public TalkReceive(String receiverName, String messageFrom, int listenerPort){
        this.receiverName = receiverName;
        this.messageFrom = messageFrom;
        this.listenerPort = listenerPort;
        try {
            // 1、创建套接字 监听端口
            receiver = new DatagramSocket(listenerPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(receiverName + " receiver running...");
        receive();
        System.out.println("对方关闭连接，" + receiverName + " receiver closed!!!");
        System.out.print(receiverName + ">");
    }

    private void receive(){
        try {
            while (true){
                // 2、创建缓存
                byte[] container = new byte[CONTAINER_SIZE];
                // 3、创建数据包对象
                DatagramPacket packet = new DatagramPacket(container, 0, container.length);
                // 4、接收数据包（synchronized 同步阻塞）
                receiver.receive(packet);
                // 5、获取数据包里的数据
                byte[] data = packet.getData();
                String message = new String(data, 0, data.length).trim();
                // 6、打印数据
                System.out.println(messageFrom + "("+ listenerPort +") : " + message);
                System.out.print(receiverName + ">");
                if ("bye".equals(message.trim())){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 7、关闭资源
        if (receiver != null) {
            receiver.close();
        }
    }
}
```

#### 测试

##### TalkStudent

```java
public class TalkStudent {
    public static void main(String[] args) {

        //================== student ===> teacher ===================//
        /** (send)
         * sendorName : 小爱同学
         * toIp : 127.0.0.1 (老师的ip地址)
         * toPort : 8080 (老师监听的端口)
         */
        String sendorName = "小爱同学";
        String toIp = "127.0.0.1";
        int toPort = 8080;
        // 开启 发送线程
        new Thread(new TalkSend(sendorName, toIp, toPort)).start();
        //============================end============================//

        //================== student <=== teacher ===================//
        /** (receive)
         * receiveName : 小爱同学
         * messageFrom : 发送方名字 (刘老师)
         * listenerPort : 8000 (我方监听端口)
         */
        String receiveName = "小爱同学";
        String messageFrom = "刘老师";
        int listenerPort = 8000;

        // 开启 接收线程
        new Thread(new TalkReceive(receiveName, messageFrom, listenerPort)).start();
        //============================end============================//
    }
}
```

##### TalkTeacher

```java
public class TalkTeacher {
    public static void main(String[] args) {
        //================== teacher ===> student ===================//
        /** (send)
         * sendorName : 刘老师
         * toIp : 127.0.0.1 (小爱同学的ip地址)
         * toPort : 8000 (小爱同学监听的端口)
         */
        String sendorName = "刘老师";
        String toIp = "127.0.0.1";
        int toPort = 8000;

        // 开启 发送线程
        new Thread(new TalkSend(sendorName, toIp, toPort)).start();
        //============================end============================//


        //================== teacher <=== student ===================//
        /** (receive)
         * receiveName : 刘老师
         * messageFrom : 发送方名字 (小爱同学)
         * listenerPort : 8080 (我方监听端口)
         */
        String receiveName = "刘老师";
        String messageFrom = "小爱同学";
        int listenerPort = 8080;

        // 开启 接收线程
        new Thread(new TalkReceive(receiveName, messageFrom, listenerPort)).start();
        //============================end============================//
    }
}
```

**效果展示**

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/udp02.gif)



## 网络编程：URL

url统一资源定位符：<协议://ip地址:端口/资源路径?查询字符串>

```java
public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://baike.baidu.com/item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%B3%BB%E7%BB%9F?fromtitle=url&fromid=110640");

        int port = url.getPort();// 端口
        System.out.println(port); // -1
        
        String authority = url.getAuthority();
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
```

### 通过url下载网络资源

```java
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
```

**效果展示**

![](https://liuyou-images.oss-cn-hangzhou.aliyuncs.com/markdown/image-20210130194709533.png)

