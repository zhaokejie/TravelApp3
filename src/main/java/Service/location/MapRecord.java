package Service.location;

import Dao.AccountDaoImpl;
import Dao.MapRecordDaoImpl;
import Dao.MyBatisConnect;
import Service.user.Account;
import Service.user.AccountDao;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MapRecord {

    int id;

    Date saveTime;
    String userName;
    String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public static void saveMapRecord(MapRecord mapRecord) throws IOException {

        //获取连接
        AccountDao accountDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        MapRecordDao mapRecordDao = new MapRecordDaoImpl(sqlSession);


        mapRecordDao.insertMapRecord(mapRecord);
        sqlSession.commit();
        //关闭连接
        myBatisConnect.closeSqlSession();
    }

    public List<MapRecord> getMapRecordByUserName(String userName) throws IOException {

        //获取连接
        AccountDao accountDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        MapRecordDao mapRecordDao = new MapRecordDaoImpl(sqlSession);
        List<MapRecord> mapRecords = mapRecordDao.getMapRecordByUserName(userName);;


        sqlSession.commit();
        //关闭连接
        myBatisConnect.closeSqlSession();
        return mapRecords;
    }


    public JSONObject getJsonMapRecord()
    {
         JSONObject json = new JSONObject();
         json.put("id",this.id);
         json.put("saveTime",this.saveTime);
         json.put("userName",this.userName);
         json.put("path",this.path);

         return json;

    }

}




//
//    create table mapRecord
//        (
//        id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
//        saveTime char(50) not null,
//        userName varchar(100) not null,
//        path varchar(150)
//        );