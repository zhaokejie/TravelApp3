package Service.user;

import Dao.MessageDaoImpl;
import Dao.MyBatisConnect;
import Dao.UserDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Message {

    int senderID;
    int receiverID;
    String type;
    String content;
    Date sendTime;


    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }




    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    //插入一条消息到未读表中
    public static void insertMessage(Message message) throws IOException {
        MessageDao messageDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        messageDao = new MessageDaoImpl(sqlSession);
        messageDao.insertMessage(message);

        sqlSession.commit();
        myBatisConnect.closeSqlSession();
    }

    //插入一条消息到已读表中
    public static void insertSendedMessage(Message message) throws IOException {
        MessageDao messageDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        messageDao = new MessageDaoImpl(sqlSession);
        messageDao.insertSendedMessage(message);

        sqlSession.commit();
        myBatisConnect.closeSqlSession();
    }



    //从未读列表中，用接收者ID获取消息
    public static List<Message> getMessageByReceiverID(int ID) throws IOException {
        MessageDao messageDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        messageDao = new MessageDaoImpl(sqlSession);
        List<Message> messages = messageDao.getMessageListById(ID);
        myBatisConnect.closeSqlSession();


        return messages;

    }
    //从已读列表中，用发送者或接受者ID获取消息
    public static List<Message> getSendedMessageByID(int ID) throws IOException {
        MessageDao messageDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        messageDao = new MessageDaoImpl(sqlSession);
        List<Message> messages = messageDao.getSendedMessageListById(ID);
        myBatisConnect.closeSqlSession();


        return messages;

    }

    //通过接收者ID，将消息转入已读队列
    public static void readMessageByID(int ID) throws IOException {

        //从未读列中取得数据并删除
        MessageDao messageDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        messageDao = new MessageDaoImpl(sqlSession);
        List<Message> messages = messageDao.getMessageListById(ID);


        //将数据存入已读表中
        for(Message m : messages)
        {
            messageDao.insertSendedMessage(m);
        }

        sqlSession.commit();

        //删除未读列表中的消息
        messageDao.deleteMessage(ID);
        sqlSession.commit();
        myBatisConnect.closeSqlSession();


    }

    public static JSONObject messageToJSON(Message message){


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("senderID",message.getSenderID());
        jsonObject.put("receiverID",message.getReceiverID());
        jsonObject.put("type",message.getType());
        jsonObject.put("content",message.getContent());
        jsonObject.put("sendTime",message.getSendTime());

        return jsonObject;

    }



    public static void main(String[] args) {
        Message message  = new Message();
        message.setSenderID(1);
        message.setReceiverID(2);
        message.setContent("ahahaha");
        message.setType("test");
        message.setSendTime(new Date());


        System.out.println(message.toString());
    }




}
