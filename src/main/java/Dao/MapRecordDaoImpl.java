package Dao;

import Service.location.MapRecord;
import Service.location.MapRecordDao;
import Service.location.MarkPoi;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class MapRecordDaoImpl implements MapRecordDao {

    public SqlSession sqlSession;

    public MapRecordDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public MapRecord getMapRecordById(int id) {
        return this.sqlSession.selectOne("MapRecordDao.getMapRecordById",id);
    }

    @Override
    public List<MapRecord> getMapRecordByUserName(String userName) {
        return this.sqlSession.selectList("MapRecordDao.getMapRecordByUserName",userName);
    }

    @Override
    public List<MapRecord> getMapRecordAll() {
        return this.sqlSession.selectList("MapRecordDao.getMapRecordAll");
    }

    @Override
    public void insertMapRecord(MapRecord mapR) {
        this.sqlSession.insert("MapRecordDao.insertMapRecord",mapR);
    }

    @Override
    public void updateMapRecord(MapRecord mapR) {

    }

    @Override
    public void deleteMapRecord(String ID) {
        this.sqlSession.delete("MapRecordDao.deleteMapRecord",ID);

    }
}
