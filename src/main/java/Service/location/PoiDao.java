package Service.location;


import java.util.List;

public interface PoiDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public List<MarkPoi> getPoiById(int id);

    //    根据用户名查询用户信息
    public List<MarkPoi> getPoiByPoiName(String PoiName);



    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<MarkPoi> getPoiAll();

    /**
     * 新增用户
     *
     * @param Poi
     */
    public void insertPoi(MarkPoi Poi);

    /**
     * 更新用户信息
     *
     * @param Poi
     */
    public void updatePoi(MarkPoi Poi);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deletePoi(String id);
}