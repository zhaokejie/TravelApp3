package Service.user;

import Dao.MyBatisConnect;
import Dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class User {

//成员变量
	private int ID;
	private	String userName;
	private String headPicSrc;
	private String Email;
	private List<String> friendsId;
	private HashMap friendsHeadPicUrl;

	public HashMap getFriendsNameList() {
		return friendsNameList;
	}

	public void setFriendsNameList(HashMap friendsNameList) {
		this.friendsNameList = friendsNameList;
	}

	private HashMap friendsNameList;

	public HashMap getFriendsHeadPicUrl() {
		return friendsHeadPicUrl;
	}

	public void setFriendsHeadPicUrl(HashMap friendsHeadPicUrl) {
		this.friendsHeadPicUrl = friendsHeadPicUrl;
	}




	public static User getUserByID(int ID) throws IOException {
		UserDao userDao;
		MyBatisConnect myBatisConnect = new MyBatisConnect();
		SqlSession sqlSession = myBatisConnect.getSqlSession();
		userDao = new UserDaoImpl(sqlSession);
		User user = userDao.getUserById(ID);



		user.setFriendsId(Friends.getFriendsIDList(ID));


		HashMap headPic = new HashMap();
		HashMap friendNameList = new HashMap();
		for(String u :user.getFriendsId())
		{
			User aFriend = userDao.getUserById(Integer.valueOf(u));
//			User aFriend = User.getUserByID(Integer.valueOf(u));//产生了递归问题
			headPic.put(u,aFriend.getHeadPicSrc());
			friendNameList.put(u,aFriend.getUserName());

		}
		user.setFriendsHeadPicUrl(headPic);
		user.setFriendsNameList(friendNameList);
		myBatisConnect.closeSqlSession();
		return user;
	}

	public static int getIDByName(String userName) throws IOException {
		UserDao userDao;
		MyBatisConnect myBatisConnect = new MyBatisConnect();
		SqlSession sqlSession = myBatisConnect.getSqlSession();
		userDao = new UserDaoImpl(sqlSession);
		User user = userDao.getUserByName(userName);
		if(user != null) {
			int ID = user.getID();
			return ID;
		}
		else
		{
			return -1;
		}




	}

	public static void insertUser(int ID,String userName, String Email) throws IOException {
		User user = new User();
		user.setUid(ID);
		user.setEmail(Email);
		user.setUserName(userName);
		user.setHeadPicSrc("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607925487239&di=9d8560554052d3ab14db57cc5cd2d4e2&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F0cd238587a0984d24b8688ad35c187da3ace5314317c-KPcKiS_fw658");

		//mybatis初始化
		AccountDao accountDao;
		SqlSession sqlSession;
		// mybatis-config.xml
		String resource = "mybatis-config.xml";
		// 读取配置文件
		InputStream is = Resources.getResourceAsStream(resource);
		// 构建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		// 获取sqlSession
		sqlSession = sqlSessionFactory.openSession();

		UserDao userdao = new UserDaoImpl(sqlSession);
		userdao.insertUser(user);

		sqlSession.commit();

		sqlSession.close();



	}

	public static JSONObject getUserJSON(User user) throws IOException {
		JSONObject json = new JSONObject();
		json.put("ID",user.getID());
		json.put("userName",user.getUserName());
		json.put("headPicSrc",user.getHeadPicSrc());
		json.put("Email",user.getEmail());
		json.put("friendsId",user.getFriendsId());
		json.put("friendsHeadPicUrl",user.getFriendsHeadPicUrl());
		json.put("friendsNameList",user.getFriendsNameList());

		return json;
	}

//Getter & Setter
	public int getID() {
		return ID;
	}

	public void setUid(int ID) {
		this.ID = ID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadPicSrc() {
		return headPicSrc;
	}

	public void setHeadPicSrc(String headPicSrc) {
		this.headPicSrc = headPicSrc;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public List<String> getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(List<String> friendsId) {
		this.friendsId = friendsId;
	}




	}
