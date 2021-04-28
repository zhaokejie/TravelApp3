package Service.user;


import java.util.List;

public interface FriendsDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public List<Friends> getFriendsListById(int id);

    //    根据用户名查询用户信息



    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<Friends> getFriendsAll();

    /**
     * 新增用户
     *
     * @param friends
     */
    public void insertFriends(Friends friends);

    /**
     * 更新用户信息
     *
     * @param friends
     */
    public void updateAccount(Friends friends);

    /**
     * 根据id删除用户信息
     *
     * @param
     */
    public void deleteFriends(String myID, String friendID);


}