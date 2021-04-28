package Service.user;


import java.util.List;

public interface UserDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public User getUserById(int id);

    //    根据用户名查询用户信息
    public User getUserByName(String userName);


    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> getUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deleteUser(String id);
}