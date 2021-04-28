package Dao;

import Service.user.Friends;
import Service.user.FriendsDao;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class FriendsDaoImpl implements FriendsDao {
    public SqlSession sqlSession;

    public FriendsDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Friends> getFriendsListById(int id) {
        return this.sqlSession.selectList("FriendsDao.getFriendsListById",id);
    }

    @Override
    public List<Friends> getFriendsAll() {
        return this.sqlSession.selectList("FriendsDao.getFriendsAll");
    }

    @Override
    public void insertFriends(Friends friends) {
        this.sqlSession.insert("FriendsDao.insertFriends",friends);

    }

    @Override
    public void updateAccount(Friends friends) {

    }

    @Override
    public void deleteFriends(String myID, String friendID) {
        HashMap map = new HashMap();
        map.put("myID",myID);
        map.put("friendID",friendID);

        this.sqlSession.delete("FriendsDao.deleteFriends",map);
    }
}
