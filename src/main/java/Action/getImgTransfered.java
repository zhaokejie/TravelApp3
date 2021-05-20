package Action;

import Service.tools.PhotoTools;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "getImgTransfered", value = "/getImgTransfered")
public class getImgTransfered extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picUrl = request.getParameter("picUrl");
        String img = PhotoTools.encodeToString(picUrl,"jpg");
        response.setHeader("Access-Control-Expose-Headers","ifExist");
        if (img != null)
        {
            response.getWriter().write(img);
            response.setHeader("ifExist","1");

        }
        else{
            response.setHeader("ifExist","0");
        }

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
