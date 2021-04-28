package Service.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionsManager {
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	
	public static void addSession(String name, HttpSession session)
	{
		SessionsManager.sessions.put(name, session);
	}
	public static boolean haveSession(String name)
	{
		if(sessions.containsKey(name))
			return true;
		else
			return false;
	}
	public static void removeSession(String name)
	{
		if(SessionsManager.haveSession(name))
		{
			System.out.println("主动删除session");
			HttpSession ahs = sessions.get(name);
//			sessions.remove(name);
			ahs.invalidate();
		}
			
	}
	public static void outSession(String name)
	{
		if(SessionsManager.haveSession(name))
		{
			System.out.println("踢出session管理器");
			
			sessions.remove(name);
			
		}
			
	}

	public static String viewSessions()
	{
		String s = "sessions:\n";
		for(HttpSession session : sessions.values())
		{
			s = s+"\n" + session;
		}
		return s;
	}
}
