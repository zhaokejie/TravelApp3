package Action;

import Service.user.Account;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DoRegister",urlPatterns = "/DoRegister")
public class DoRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式相关
        request.setCharacterEncoding("utf-8");


        //获取前端传来的数据
        HttpSession hs = request.getSession();
        String emailCodeSaved = (String) hs.getAttribute("EmailCode");
        String name = request.getParameter("uname");
        String password = request.getParameter("pwd");
        String email = request.getParameter("anEmail");
        String emailCode = request.getParameter("EmailCode");

        //返回值的json数据
        JSONObject respJson = new JSONObject();

        System.out.println("emailCode:"+emailCode+"   emailCodeSaved:"+emailCodeSaved);
        if(emailCode.compareTo(emailCodeSaved)!=0)
        {
            System.out.println("邮箱验证码错误");
            respJson.put("State","2");
//            response.getOutputStream().write("用户名已使用".getBytes("UTF-8"));
        }
        else if(Account.LoginService("uname","pwd")!=null)
        {
            System.out.println("用户名已使用");
            respJson.put("State","3");
//            response.getOutputStream().write("用户名已使用".getBytes("UTF-8"));
        }
        else
        {
//            String uid = java.util.UUID.randomUUID().toString();
            int uID = Account.registerService(name,password,email);

            respJson.put("State","1");
            respJson.put("uID",uID);
            System.out.println("注册成功");

        }
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
