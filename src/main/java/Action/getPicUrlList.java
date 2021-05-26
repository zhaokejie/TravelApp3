package Action;

import Service.location.MapRecord;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getPicUrlList", value = "/getPicUrlList")
public class getPicUrlList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");

        List<MapRecord> mapList = MapRecord.getMapRecordByUserName(uname);
        JSONArray jsonMapArrays = new JSONArray();

        for(MapRecord m:mapList)
        {

            jsonMapArrays.put(m.getJsonMapRecord());
        }

        response.getWriter().write(jsonMapArrays.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
