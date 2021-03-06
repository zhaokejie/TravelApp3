package Action;

import Service.location.MapRecord;
import Service.tools.ControlPython;
import Service.tools.PhotoTools;
import Service.tools.SocketClient;
import Service.user.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "imgTransfer",urlPatterns = "/imgTransfer")


public class imgTransfer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String styleIndex = request.getParameter("styleIndex");

        String shouzhangName = request.getParameter("shouzhangName");

        shouzhangName = URLDecoder.decode(shouzhangName,"UTF-8");
//        shouzhangName = new String(shouzhangName.getBytes("iso-8859-1"),"utf-8");

//        String photoData = request.getParameter("photoData");
        Reader reader = request.getReader();
        int c;

        StringBuffer buf = new StringBuffer();
        do {
            c = request.getReader().read();
            buf.append((char)c);


        }while(c != -1) ;
        String photoData = new String(buf);

        JSONObject jsonObject = new JSONObject(photoData);
        photoData = jsonObject.getString("rawMap");

        User aUser = User.getUserByID(User.getIDByName(uname));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date saveTime = new Date();
        String dateNow = df.format(saveTime);
        String picFileName = uname+dateNow;
        String picUrl = "C:\\yhj\\data\\mapTransfer\\rawMap\\"+picFileName+".jpg";
        String resPicUrl = "https://shouzhang.icu:4430/shouzhangData/stylizedMap/"+picFileName+".jpg";
        PhotoTools.decodeToImage(photoData,picUrl);



        //???????????????????????????
//        int id;
//
//        Date saveTime;
//        String userName;
//        String path;
        MapRecord map = new MapRecord();
        map.setSaveTime(saveTime);
        map.setPath(resPicUrl);
        map.setUserName(uname);
        map.setShouzhangName(shouzhangName);
        MapRecord.saveMapRecord(map);


        //??????python????????????
        JSONObject jsonPy = new JSONObject();
        jsonPy.put("picFileName" , picFileName);
        jsonPy.put("styleIndex", styleIndex);
        SocketClient.sendMessage(jsonPy.toString());
        System.out.println("-------------------jsonPy.toString()---------------------");
        System.out.println(jsonPy.toString());

        //????????????Python????????????
//        ControlPython controlPython = new ControlPython();
//        controlPython.getPythonDemo("C:\\yhj\\code\\PycharmProject\\mapTransfer\\demo.py","C:\\yhj\\data\\mapTransfer\\rawMap\\"+picFileName+".jpg");
//        MapRecord mapRecord = new MapRecord();
//        mapRecord.setUserName(uname);
//        mapRecord.setSaveTime(saveTime);
//        mapRecord.setPath(picUrl);
//        MapRecord.saveMapRecord(mapRecord);






        JSONObject jsonPicUrl = new JSONObject();
        jsonPicUrl.put("picUrl", resPicUrl);
        response.getWriter().write(jsonPicUrl.toString());

//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
