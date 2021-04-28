package Service.user;

import Dao.AccountDaoImpl;
import Dao.MyBatisConnect;
import Dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Account {
    int ID;
    String userName;
    String passWord;
    String Email;




    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public static Account LoginService(String userName,String passWord) throws IOException {

        //获取连接
        AccountDao accountDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        accountDao = new AccountDaoImpl(sqlSession);
        Account account = new Account();
//        accountDao = new AccountDaoImpl(sqlSession);

        Account tempAccount = accountDao.getAccountByName(userName);

        //关闭连接
        myBatisConnect.closeSqlSession();


        //判断用户名密码是否匹配
        if(tempAccount == null)
        {
            return null;
        }else if(tempAccount.passWord.equals(passWord))
        {
            return tempAccount;
        }else {
            return null;
        }
    }


    public static Account getAccountByEmail(String Email)  {


        AccountDao accountDao;
        MyBatisConnect myBatisConnect = null;
        try {
            myBatisConnect = new MyBatisConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        accountDao = new AccountDaoImpl(sqlSession);
        //判断用户名密码是否匹配
        Account tempAccount = accountDao.getAccountByEmail(Email);
        myBatisConnect.closeSqlSession();


        return tempAccount;

    }

    public static void insertAccount(String userName, String pwd,String Email) throws IOException {

        //获取连接
        AccountDao accountDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        accountDao = new AccountDaoImpl(sqlSession);
        Account account = new Account();
        account.setUsername(userName);
        account.setEmail(Email);
        account.setPassword(pwd);

        accountDao.insertAccount(account);
        sqlSession.commit();
        //关闭连接
        myBatisConnect.closeSqlSession();




    }


    public static int registerService(String userName, String pwd,String Email) throws IOException {

        Account.insertAccount(userName,pwd,Email);
        Account account = Account.getAccountByEmail(Email);
        System.out.println(account.getID()+account.getEmail());
        User.insertUser(account.getID(),userName,account.getEmail());

        return account.getID();
        //返回用户的Id

    }
}
