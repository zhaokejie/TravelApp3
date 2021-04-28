package Action;

import Service.user.Friends;
import Service.user.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddFriend",urlPatterns = "/AddFriend")
public class AddFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式和参数
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers","ifOnline");

        //获取前端数据
        HttpSession httpSession = request.getSession(false);


        //添加好友
        JSONObject respJson = new JSONObject();
        String ifOnline = null;
        JSONObject userJson = null;
        int myID = Integer.valueOf(request.getParameter("myID"));
        int friendID = Integer.valueOf(request.getParameter("friendID"));
        if(httpSession == null)
        {
            ifOnline = "0";
            response.setHeader("ifOnline","0");
        }
        else
        {
            ifOnline = "1";
            User user = (User) httpSession.getAttribute("aUser");
            Friends.insertAFriend(myID,friendID);
            Friends.insertAFriend(friendID,myID);
            user = User.getUserByID(user.getID());
            httpSession.setAttribute("aUser",user);
            userJson = User.getUserJSON(user);

        }

        //返回获取到的信息和状态
        respJson.put("ifOnline",ifOnline);
        respJson.put("userData",userJson);
        response.setHeader("ifOnline",ifOnline);
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
