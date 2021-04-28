package Dao;

import Service.user.User;
import Service.user.UserDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {

    public SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public User getUserById(int id) {
        return this.sqlSession.selectOne("UserDao.getUserById", id);
    }

    @Override
    public User getUserByName(String userName) {
        return this.sqlSession.selectOne("UserDao.getUserByName",userName);
    }

    @Override
    public List<User> getUserAll() {
        return this.sqlSession.selectList("UserDao.getUserAll");
    }

    @Override
    public void insertUser(User user) {
        this.sqlSession.insert("UserDao.insertUser",user);
    }

    @Override
    public void updateUser(User user) {

        this.sqlSession.update("UserDao.updateUser",user);
    }

    @Override
    public void deleteUser(String id) {
        this.sqlSession.delete("UserDao.deleteUser",id);
    }
}
