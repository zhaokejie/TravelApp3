package Action;

import Service.tools.VerifyCodeTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//import static jdk.javadoc.internal.doclets.toolkit.util.StandardDocFileFactory.newFile;

@WebServlet(name = "getVerifyCode",urlPatterns="/getVerifyCode")
public class getVerifyCode extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //获取客户端请求信息

        HttpSession session = request.getSession();


        VerifyCodeTools.createVerifyCode(session,response.getOutputStream());
        String VerifyCode = (String) session.getAttribute("sessionCacheData");
//        response.setHeader("Access-Control-Expose-Headers","vCode");
//        response.setHeader("vCode",VerifyCode);




    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
