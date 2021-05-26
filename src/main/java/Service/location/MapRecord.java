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

    String shouzhangName;



    public String getShouzhangName() {
        return shouzhangName;
    }

    public void setShouzhangName(String shouzhangName) {
        this.shouzhangName = shouzhangName;
    }

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

    public static List<MapRecord> getMapRecordByUserName(String userName) throws IOException {

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
         json.put("shouzhangName",this.shouzhangName);

         return json;

    }

    public static void removeMap(String ID) throws IOException {
        //获取连接
        AccountDao accountDao;
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        MapRecordDao mapRecordDao = new MapRecordDaoImpl(sqlSession);
        mapRecordDao.deleteMapRecord(ID);


        sqlSession.commit();
        //关闭连接
        myBatisConnect.closeSqlSession();

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
//ALTER TABLE mapRecord ADD COLUMN shouzhangName VARCHAR(100) NOT NULL AFTER path;
