package xyz.liuyou.udp.runnable_talk;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/31 15:18
 * @decription
 **/
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