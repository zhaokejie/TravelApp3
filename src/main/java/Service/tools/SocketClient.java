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

    static Socket socket;

    static
    {
        try {socket = new Socket("127.0.0.1",50006);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[])throws Exception {

        try {

            //获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write("我是Java服务器");
            pw.flush();
            socket.shutdownOutput();//关闭输出流

            InputStream is=socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=in.readLine())!=null){
                System.out.println("我是客户端，Python服务器说："+info);
            }
            is.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeSocket()
    {
        try {
            socket.shutdownOutput();//关闭输出流
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void sendMessage(String mess)
    {
        try {

            System.out.println("------------SocketClient----------");
            System.out.println(mess);
            //获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write(mess);
            pw.flush();

            InputStream is=socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=in.readLine())!=null){
                System.out.println("Python服务器回复："+info);
            }
            is.close();
            in.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}