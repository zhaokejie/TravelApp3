package Dao;

import Service.location.MarkPoi;
import Service.location.PoiDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PoiDaoImpl implements PoiDao {
    public SqlSession sqlSession;

    public PoiDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }



    @Override


    public List<MarkPoi> getPoiById(int id) {
        return this.sqlSession.selectList("PoiDao.getPOIById", id);
    }

    @Override
    public List<MarkPoi> getPoiByPoiName(String PoiName) {
        return this.sqlSession.selectList("PoiDao.getPOIByPoiName", PoiName);
    }



    @Override
    public List<MarkPoi> getPoiAll() {
        return this.sqlSession.selectList("PoiDao.getUserAll");
    }

    @Override
    public void insertPoi(MarkPoi Poi) {
        this.sqlSession.insert("PoiDao.insertPOI",Poi);
    }

    @Override
    public void updatePoi(MarkPoi Poi) {
        this.sqlSession.update("PoiDao.updatePOI",Poi);
    }

    @Override
    public void deletePoi(String id) {
        this.sqlSession.delete("PoiDao.updatePOI",id);
    }
}
