package Action;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "judgeVerifyCode",urlPatterns = "/judgeVerifyCode")
public class judgeVerifyCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vCode = request.getParameter("vCode");
        HttpSession session = request.getSession(false);
        response.setHeader("Access-Control-Expose-Headers","vCode");
        String remoteCode = (String)session.getAttribute("vCode");
        JSONObject respJson = new JSONObject();

        if(vCode.compareTo(remoteCode) == 0)
        {
            respJson.put("ifRight","1");
            response.setHeader("ifAgree","1");
        }else
        {
            respJson.put("ifRight","0");
            response.setHeader("ifAgree","0");
        }
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vCode = request.getParameter("vCode");
        HttpSession session = request.getSession(false);
        response.setHeader("Access-Control-Expose-Headers","vCode");
        String remoteCode = (String)session.getAttribute("vCode");
        JSONObject respJson = new JSONObject();

        if(vCode.compareTo(remoteCode) == 0)
        {
            respJson.put("ifRight","1");
            response.setHeader("ifAgree","1");
        }else
        {
            respJson.put("ifRight","0");
            response.setHeader("ifAgree","0");
        }
        response.getOutputStream().write(respJson.toString().getBytes("UTF-8"));

    }
}
