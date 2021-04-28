package Action;

import Service.location.MarkPoi;
import Service.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(name = "getPOIByUid",urlPatterns = "/getPOIByUid")
public class getPOIByUid extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //定义状态
        String state;

        //获取信息和Session
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession httpsession = request.getSession(false);
        User user = (User) httpsession.getAttribute("aUser");

        //从服务器拿数据
        List<MarkPoi> poiList = MarkPoi.getMarkPoiByUID(user.getID());

        //数据处理
        JSONArray jsonArray = new JSONArray();
        for(MarkPoi poi:poiList)
        {
            JSONObject poiJson = null;
            try {
                poiJson = MarkPoi.getMarkPoiJSON(poi);
            } catch (ParseException e) {
                e.printStackTrace();
                state = "0";

            }
            jsonArray.put(poiJson);
        }
        state = "1";

        //返回数据到前端
        JSONObject respJson = new JSONObject();
        respJson.put("state",state);
        respJson.put("poiList",jsonArray);
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
