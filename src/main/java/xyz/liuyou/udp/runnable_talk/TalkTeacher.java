package xyz.liuyou.udp.runnable_talk;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/31 15:18
 * @decription
 **/
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
