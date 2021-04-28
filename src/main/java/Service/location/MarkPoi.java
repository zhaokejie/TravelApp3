package Service.location;

import Dao.AccountDaoImpl;
import Dao.MyBatisConnect;
import Dao.PoiDaoImpl;
import Service.user.Account;
import Service.user.AccountDao;
import Service.user.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MarkPoi {
    int userID;
    Date time;
    String longitude;
    String latitude;
    String poiName;
    String poiType;
    String weatherCode;

    public static Date StringToDate(String dateS) throws ParseException {

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.parse(dateS);
    }

    public static String DateToString(Date date) throws ParseException {

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String sDate = ft.format(date);

        return sDate;
    }

    public static void doMarkPOI (MarkPoi poi) throws IOException {
        //获取连接
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        PoiDao poiDao = new PoiDaoImpl(sqlSession);

        //插入POI信息
        poiDao.insertPoi(poi);
        sqlSession.commit();

        //关闭连接
        myBatisConnect.closeSqlSession();


    }

    public static List<MarkPoi> getMarkPoiByUID(int uid) throws IOException {
        //获取连接
        MyBatisConnect myBatisConnect = new MyBatisConnect();
        SqlSession sqlSession = myBatisConnect.getSqlSession();
        PoiDao poiDao = new PoiDaoImpl(sqlSession);

        //获取POI信息
        List<MarkPoi> poiList = poiDao.getPoiById(uid);

        //关闭连接
        myBatisConnect.closeSqlSession();

        //返回数据
        return poiList;


    }

    public static JSONObject getMarkPoiJSON(MarkPoi markPoi) throws ParseException {
        JSONObject json = new JSONObject();
        json.put("ID",markPoi.getUserID());
        json.put("latitude",markPoi.getLatitude());
        json.put("longitude",markPoi.getLongitude());
        json.put("time",DateToString(markPoi.getTime()));
        json.put("poiName",markPoi.getPoiName());
        json.put("poiType",markPoi.getPoiType());
        json.put("weatherCode",markPoi.getWeatherCode());


        return json;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }





}

