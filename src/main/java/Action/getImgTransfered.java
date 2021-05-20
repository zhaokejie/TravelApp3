package Action;

import Service.tools.PhotoTools;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "getImgTransfered", value = "/getImgTransfered")
public class getImgTransfered extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picUrl = request.getParameter("picUrl");
        String img = null;
        if(picUrl != null)
        {
            img = PhotoTools.encodeToString(picUrl,"jpg");
        }

        response.setHeader("Access-Control-Expose-Headers","ifExist");
        JSONObject jsonRes = new JSONObject();
        if (img != null)
        {
            response.setHeader("ifExist","1");
            jsonRes.put("ifExist","1");
            jsonRes.put("picData",img);
        }
        else{
            response.setHeader("ifExist","0");
            jsonRes.put("ifExist","0");
            jsonRes.put("picData","-1");


        }
        response.getWriter().write(jsonRes.toString());


//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
