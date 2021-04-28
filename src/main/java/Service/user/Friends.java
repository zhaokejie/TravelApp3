package Service.user;

import Dao.FriendsDaoImpl;
import Dao.MyBatisConnect;
import Dao.UserDaoImpl;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Friends {
    int myID;
    int friendID;
    Date connectTime;

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public Date getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    public static List<String> getFriendsIDList(int ID) throws IOException {
        FriendsDao friendsDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        friendsDao = new FriendsDaoImpl(sqlSession);
        List<Friends> friends = friendsDao.getFriendsListById(ID);


        List<String> friendsIDList = new ArrayList<String>();
        for(Friends f : friends)
        {
            friendsIDList.add(String.valueOf(f.friendID));
        }

        myBatisConnect.closeSqlSession();


        return friendsIDList;
    }

    public static void insertAFriend(int myID , int friendID) throws IOException {
        Friends friends  = new Friends();

        friends.setMyID(myID);
        friends.setFriendID(friendID);;
        friends.setConnectTime(new Date());

        FriendsDao friendsDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        friendsDao = new FriendsDaoImpl(sqlSession);
        friendsDao.insertFriends(friends);
        sqlSession.commit();
        myBatisConnect.closeSqlSession();

        return;
    }

}
