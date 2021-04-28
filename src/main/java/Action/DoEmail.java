package Action;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Service.user.Account;
import com.sun.mail.util.MailSSLSocketFactory;
import org.json.JSONObject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoRegister
 */

@WebServlet("/DoEmail")
public class DoEmail extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的格式和参数
        request.setCharacterEncoding("utf-8");


        //获取客户端的请求数据v
        String anEmail = request.getParameter("anEmail");
        HttpSession hs = request.getSession();


        //进行判断并设置响应数据
        JSONObject respJson = new JSONObject();
        if(Account.getAccountByEmail(anEmail) == null)
        {
            String emailCode = sendEmail(anEmail);
            if(emailCode != null)
            {
                //发送成功
                System.out.println("发送成功");
                respJson.put("state","1");
                respJson.put("EmailCode",emailCode);
                hs.setAttribute("EmailCode",emailCode);
            }
            else {
                //发送失败
                System.out.println("发送失败");
                respJson.put("state","2");
//                respJson.put("EmailCode",emailCode);
//                hs.setAttribute("EmailCode",emailCode);

            }

        }
        else
        {
            //邮箱已注册
            System.out.println("邮箱已注册");
            respJson.put("state","3");

        }

        //返回响应数据到客户端
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));

    }

    public static String sendEmail(String anEmail)
    {
        String result="";
        try {

            for(int i=0;i<6;i++){
                //生成97-122的int型的整型
                int intValue=(int)(Math.random()*26+97);
                //将intValue强制转化成char类型后接到result后面
                result=result+(char)intValue;
            }
            Properties props = new Properties();

            // 开启debug调
//            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.qq.com");
            // props.setProperty("mail.port", "465");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props);

            Message msg = new MimeMessage(session);
            msg.setSubject("请确认您的操作");
            StringBuilder builder = new StringBuilder();
            //            builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
//            builder.append("这是一条来自travel手账app的验证消息由于您正在执行某些操作，我们需要验证是否为您本人操作,您的验证码为："+result);
//            builder.append("\n时间 " + new Date());
//            msg.setText("您的数字为："+result);
            String text = "<head>\n" +
                    "    <base target=\"_blank\" />\n" +
                    "    <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
                    "    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
                    "    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                    "        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                    "        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                    "        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                    "        img{ border:0}\n" +
                    "        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                    "        blockquote{margin-right:0px}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body tabindex=\"0\" role=\"listitem\">\n" +
                    "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                    "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                    "                    <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
                    "                </table>\n" +
                    "            </div>\n" +
                    "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                    "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                    "                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的旅行手账用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\n" +
                    "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                    "                        您正在进行<span style=\"color: red\">注册账号</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">"+result+
                    "</span>，以完成操作。\n" +
                    "                    </strong>\n" +
                    "                </div>\n" +
                    "                <div style=\"margin-bottom:30px;\">\n" +
                    "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                    "                        <p style=\"color:#747474;\">\n" +
                    "                            注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                    "                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                    "                        </p>\n" +
                    "                    </small>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div style=\"width:700px;margin:0 auto;\">\n" +
                    "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                    "                    <p>此为系统邮件，请勿回复<br>\n" +
                    "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                    "                    </p>\n" +
                    "                    <p>TravelApp团队</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table>\n" +
                    "</body>";
//            msg.setText(text);
            msg.setContent(text,"text/html;charset=utf-8");
            msg.setFrom(new InternetAddress("2793118771@qq.com"));

            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com", "2793118771@qq.com", "pclndfbhwdfodehe");
//            System.out.println("hehe"+anEmail);
            transport.sendMessage(msg, new Address[] { new InternetAddress(anEmail) });
            System.out.println("hehheeh");
            transport.close();


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;


    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


