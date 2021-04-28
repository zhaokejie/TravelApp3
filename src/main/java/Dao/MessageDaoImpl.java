package Dao;

import Service.user.Message;
import Service.user.MessageDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MessageDaoImpl implements MessageDao {
    public SqlSession sqlSession;

    public MessageDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Message> getMessageListById(int id) {
        return this.sqlSession.selectList("MessageDao.getMessageListById",id);
    }

    @Override
    public List<Message> getSendedMessageListById(int id) {
        return this.sqlSession.selectList("MessageDao.getSendedMessageListById",id);
    }

    @Override
    public void insertMessage(Message message) {
        this.sqlSession.insert("MessageDao.insertMessage",message);
    }

    @Override
    public void insertSendedMessage(Message message) {
        this.sqlSession.insert("MessageDao.insertSendedMessage",message);
    }

    @Override
    public void deleteMessage(int ID) {
        this.sqlSession.delete("MessageDao.deleteMessagebySendID",ID);
    }

    public void deleteSendedMessage(int ID) {
        this.sqlSession.delete("MessageDao.deleteSendedMessagebySendID",ID);
    }
}
