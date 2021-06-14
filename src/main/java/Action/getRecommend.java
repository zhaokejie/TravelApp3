package Action;

import Service.location.MapRecord;
import Service.location.MarkPoi;
import Service.tools.SocketClient;
import Service.user.Account;
import Service.user.User;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getRecommend", value = "/getRecommend")
public class getRecommend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String cityNow = request.getParameter("cityNow");
        String uname =   request.getParameter("uname");

        List<MarkPoi> markPoiList = MarkPoi.getMarkPoiByUID(User.getIDByName(uname));

        JSONObject poiJson  = new JSONObject();
        poiJson.put("uname",uname);
        poiJson.put("cityNow",cityNow);
        poiJson.put("history",markPoiList);

        String Info = SocketClient.sendMessageRe(poiJson.toString());

        response.getWriter().write(Info);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
