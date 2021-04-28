package Action;

import Service.user.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserInfoByName",urlPatterns = "/UserInfoByName")
public class UserInfoByName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式和参数
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers","ifOnline");

        //获取前端数据
        HttpSession httpSession = request.getSession(false);
        String aName = request.getParameter("uname");


        //对登陆状况进行判断
        JSONObject respJson = new JSONObject();
        String ifOnline = null;
        String ifFriend = "0";
        int ID = User.getIDByName(aName);

        JSONObject userJson = null;
        if(ID != -1)
        {
            if(!WebSocket.getClients().containsKey("ID"))
            {
                ifOnline = "0";
                response.setHeader("ifOnline","0");
            }
            else
            {
                ifOnline = "1";
                response.setHeader("ifOnline","1");

            }
            User user = User.getUserByID(ID);
            userJson = User.getUserJSON(user);
            User myUser = (User) httpSession.getAttribute("aUser");

            if(myUser.getFriendsId().contains(String.valueOf(ID)))
            {
                ifFriend = "1";
            }
            //返回获取到的信息和状态
            respJson.put("ifContain","1");
            respJson.put("ifOnline",ifOnline);
            respJson.put("userData",userJson);
            respJson.put("ifFriend",ifFriend);
            response.setHeader("ifOnline",ifOnline);
        }
        else
        {
            //返回获取到的信息和状态
            respJson.put("ifContain","0");

        }



        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
