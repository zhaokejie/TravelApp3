package Action;

import Service.location.MapRecord;
import Service.tools.PhotoTools;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "removeMap", value = "/removeMap")
public class removeMap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要删除的手帐编号和path
        String picPath = request.getParameter("picPath");
        String id = request.getParameter("ID");

        MapRecord.removeMap(id);
        PhotoTools.removePic(picPath);





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
