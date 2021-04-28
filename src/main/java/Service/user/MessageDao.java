package Service.user;

import java.util.List;

public interface MessageDao {


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public List<Message> getMessageListById(int id);

    //    根据用户名查询用户信息
    public List<Message> getSendedMessageListById(int id);

    //    根据用户名查询用户信息



    /**
     * 新增用户
     *
     * @param message
     */
    public void insertMessage(Message message);

    public void insertSendedMessage(Message message);

    /**
     * 更新用户信息
     *
     * @param message
     */

    /**
     * 根据id删除用户信息
     *
     * @param
     */
    public void deleteMessage(int ID);

    public void deleteSendedMessage(int ID);
}
