package Action;

import Service.location.MapRecord;
import Service.tools.ControlPython;
import Service.tools.PhotoTools;
import Service.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "imgTransfer",urlPatterns = "/imgTransfer")
public class imgTransfer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String photoData = request.getParameter("photoData");
        User aUser = User.getUserByID(User.getIDByName(uname));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date saveTime = new Date();
        String dateNow = df.format(saveTime);
        String picFileName = uname+dateNow;
        String picUrl = "/usr/local/TravelApp/data/"+picFileName;
        PhotoTools.GenerateImage(photoData,picUrl);

        ControlPython controlPython = new ControlPython();
        controlPython.getPythonDemo("xxx","xxx"+picFileName);
        MapRecord mapRecord = new MapRecord();
        mapRecord.setUserName(uname);
        mapRecord.setSaveTime(saveTime);
        mapRecord.setPath(picUrl);
        MapRecord.saveMapRecord(mapRecord);


        response.getWriter().write("picUrl:"+picUrl);
        


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
