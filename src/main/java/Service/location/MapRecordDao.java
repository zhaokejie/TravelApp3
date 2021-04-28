package Service.location;


import java.util.List;
import java.util.Map;

public interface MapRecordDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public MapRecord getMapRecordById(int id);

    //    根据用户名查询用户信息
    public List<MapRecord> getMapRecordByUserName(String userName);



    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<MarkPoi> getMapRecordAll();

    /**
     * 新增用户
     *
     * @param mapR
     */
    public void insertMapRecord(MapRecord mapR);

    /**
     * 更新用户信息
     *
     * @param mapR
     */
    public void updatePoi(MarkPoi mapR);


    public void deletePoi(Map prams);
}