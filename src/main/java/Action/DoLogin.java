package Action;


import Service.user.Account;
import Service.user.User;
import org.json.JSONObject;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class DoLogin
 */


@WebServlet(name = "/DoLogin",urlPatterns = "/DoLogin")
public class DoLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub


        //获取请求数据
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String name = req.getParameter("uname");
        String password = req.getParameter("pwd");

        //设置跨域请求
        //resp.setHeader("Access-Control-Expose-Headers","LoginState,SessionID");
        //resp.setHeader("Access-Control-Allow-Credentials","true");
        //resp.setHeader("Access-Control-Expose-Headers","LoginState");


        //调用登录服务
        
        Account account = Account.LoginService(name,password);
        resp.setHeader("LoginState","0");
        resp.setHeader("Access-Control-Expose-Headers","LoginState");


        //判断登录以及处理
        JSONObject respJson = new JSONObject();
        String loginState;

        if(account != null)
        {
            loginState = "1";

            //进行Session处理
            User user = User.getUserByID(account.getID());
            HttpSession hs = req.getSession(false);
            if(hs != null)
            {
                hs.invalidate();
            }//如果当前Session存在则销毁重新创建
            hs = req.getSession(true);
            hs.setMaxInactiveInterval(1*60*60);
            hs.setAttribute("aUser",user);
        }
        else
        {
            loginState = "0";
        }

        //返回响应数据
        respJson.put("LoginState",loginState);
        resp.setHeader("LoginState",loginState);
        resp.getOutputStream().write(respJson.toString().getBytes("UTF-8"));
//      resp.setHeader("SessionID",hs.getId());



    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
