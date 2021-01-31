import java.util.Scanner;

/**
 * @author liuminkai
 * @version 1.0
 * @datetime 2021/1/30 21:25
 * @decription
 **/
public class test {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String next = scanner.next();
//        String replace = next.replace("\\", "/").replace(":","");
//        System.out.println("/"+replace);

        byte[] msg = "bye".getBytes();
        if ("bye".equals(msg)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
