package Action;

import Service.location.MarkPoi;
import Service.user.User;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet(name = "DoMarkPOI",urlPatterns = "/DoMarkPOI")
public class DoMarkPOI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取信息和Session
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpsession = request.getSession(false);
        User user = (User) httpsession.getAttribute("aUser");

        //获取前端的数据
        int userID = user.getID();
        Date time = new Date();
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        String poiName = request.getParameter("poiName");
        String poiType = request.getParameter("poiType");
        String weatherCode = request.getParameter("weatherCode");



        //对Poi对象进行赋值
        MarkPoi poi = new MarkPoi();
        poi.setUserID(userID);
        poi.setTime(time);
        poi.setLongitude(longitude);
        poi.setLatitude(latitude);
        poi.setPoiName(poiName);
        poi.setPoiType(poiType);
        poi.setWeatherCode(weatherCode);

        //将POi对象存入持久层
        MarkPoi.doMarkPOI(poi);


        //发回反馈消息
        JSONObject respJson = new JSONObject();
        respJson.put("state","1");
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
