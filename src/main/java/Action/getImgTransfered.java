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
        String img = PhotoTools.GetImageStr(picUrl);

        response.getWriter().write(img);

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
