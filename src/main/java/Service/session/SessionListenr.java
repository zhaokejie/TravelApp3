package Service.session;//package Service.session;
//
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
///**
// * Application Lifecycle Listener implementation class SessionListenr
// *
// */
//@WebListener
//public class SessionListenr implements HttpSessionListener {
//
//    /**
//     * Default constructor.
//     */
//    public SessionListenr() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
//     */
//    public void sessionCreated(HttpSessionEvent arg0)  {
//         // TODO Auto-generated method stub
//    	System.out.println("创建session");
//    }
//
//	/**
//     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
//     */
//    public void sessionDestroyed(HttpSessionEvent arg0)  {
//    	System.out.println("监听到session销毁");
//         User u = (User)arg0.getSession().getAttribute("user");
//
//         SessionsManager.outSession(u.getName());
//    }
//
//}
