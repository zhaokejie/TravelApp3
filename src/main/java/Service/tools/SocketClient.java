package Service.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {





    public static void sendMessage(String mess) {
        try {

            Socket socket;
            socket = new Socket("127.0.0.1", 50006);
            InputStream is;
            BufferedReader in;
            OutputStream os;
            PrintWriter pw;

            System.out.println("------------SocketClient----------");
            System.out.println(mess);
            //获取输出流，向服务器端发送信息

            os = socket.getOutputStream();//字节输出流

            pw = new PrintWriter(os);//将输出流包装为打印流
            pw.write(mess);
            pw.flush();


            is = socket.getInputStream();

            in = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = in.readLine()) != null) {
                System.out.println("Python服务器回复：" + info);
            }
            socket.shutdownOutput();//关闭输出流
            socket.close();
            is.close();
            in.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendMessageRe(String mess) {
        String info = null;
        try {

            Socket socket;
            socket = new Socket("127.0.0.1", 50007);
            InputStream is;
            BufferedReader in;
            OutputStream os;
            PrintWriter pw;

            System.out.println("------------SocketClient----------");
            System.out.println(mess);
            //获取输出流，向服务器端发送信息

            os = socket.getOutputStream();//字节输出流

            pw = new PrintWriter(os);//将输出流包装为打印流
            pw.write(mess);
            pw.flush();


            is = socket.getInputStream();

            in = new BufferedReader(new InputStreamReader(is));

            while ((info += in.readLine()) != null) {
                System.out.println("Python服务器回复：" + info);
            }
            socket.shutdownOutput();//关闭输出流
            socket.close();
            is.close();
            in.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
}


//推荐算法的数据格式

//    static
//    {
//        try {);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


//    public static void main(String args[])throws Exception {
//
//        try {
//
//            //获取输出流，向服务器端发送信息
//            OutputStream os=socket.getOutputStream();//字节输出流
//            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
//            pw.write("我是Java服务器");
//            pw.flush();
//            socket.shutdownOutput();//关闭输出流
//
//            InputStream is=socket.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(is));
//            String info=null;
//            while((info=in.readLine())!=null){
//                System.out.println("我是客户端，Python服务器说："+info);
//            }
//            is.close();
//            in.close();
//            socket.close();
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void closeSocket()
//    {
//        try {

//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
