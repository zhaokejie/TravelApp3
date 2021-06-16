package Action;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import Service.tools.PhotoTools;
import Service.user.Account;
import Service.user.Message;
import Service.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@ServerEndpoint("/websocketWin/{userID}")
public class WebSocketWin {

    private static int onlineCount = 0;
    private static Map<String, WebSocketWin> clients = new ConcurrentHashMap<String, WebSocketWin>();
    Session session;
    String userID;

    @OnOpen
    public void onOpen(@PathParam("userID") String userID, Session session) throws IOException {

        this.userID = userID;
        this.session = session;

        addOnlineCount();
        clients.put(userID, this);
        System.out.println("已连接:"+userID);
//        sendMessageToTong("mess.toString()",userID);

        //测试连接问题
//        sendMessageTo("Hello world!",userID);



        //取出未读和已读消息
        List<Message> newMessage = Message.getMessageByReceiverID(Integer.valueOf(userID));
        List<Message> oldMessage = Message.getSendedMessageByID(Integer.valueOf(userID));


        Message.readMessageByID(Integer.valueOf(userID));
        JSONObject mess;


        for(Message m:newMessage)
        {
            mess = new JSONObject();
            mess.put("type", "unRead");
            if(m.getType().compareTo("图片") == 0)
            {
                String photoData = PhotoTools.openPhoto("C:\\yhj\\data\\websocketData" + m.getContent());
                m.setContent(photoData);

            }
            mess.put("unReadList",Message.messageToJSON(m));
            sendMessageToTong(mess.toString(),userID);
        }

        for(Message m:oldMessage)
        {
            mess = new JSONObject();
            mess.put("type", "Read");
            if(m.getType().compareTo("图片") == 0)
            {
//                  String photoData = PhotoTools.openPhoto("/usr/local/TravelApp/data/" + m.getContent());
                m.setContent("[图片聊天记录]");


            }
            mess.put("ReadList",Message.messageToJSON(m));
            sendMessageToTong(mess.toString(),userID);
        }

//            mess.put("ReadList",oldMessage);
//
//
//            sendMessageTo(mess.toString(),userID);

        System.out.println("结束for循环："+WebSocket.getClients());

    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(userID);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("实时消息发送");
//        sendMessageTo("收到了"+message,this.userID);
        System.out.println(message);

        JSONObject mess = new JSONObject(message);

        String receiverID = String.valueOf( mess.getInt("receiverID"));

        if(receiverID.compareTo("0")==0)
        {
//            sendMessageTo("收到了系统消息",this.userID);
            //发送给系统的消息
        }else if(clients.containsKey(receiverID))
        {
//            sendMessageTo("发送消息",this.userID);
            JSONObject sendMess = new JSONObject();
            sendMess.put("unReadList",mess);
            sendMess.put("ReadList","");
            sendMess.put("type", "instant");
            sendMessageToTong(sendMess.toString(),receiverID);


            //将发送过的消息存入已读列表
            Message saveMess = new Message();
            saveMess.setSenderID(Integer.valueOf(userID));
            saveMess.setReceiverID(mess.getInt("receiverID"));
            //如果为图片消息，则不保存图片记录
            if(mess.getString("type").compareTo("图片") == 0)
            {

                //旧版本方案，不存图片
//                saveMess.setType(mess.getString("type"));
//                saveMess.setContent("[该条消息为图片消息]");

                //新版本方案，存图片文件和路径
                saveMess.setType(mess.getString("type"));
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateTime=df.format(new Date());
                String picPath = mess.getInt("receiverID")+mess.getInt("senderID")+dateTime;
                saveMess.setContent(picPath);
                PhotoTools.savaPhoto(mess.getString("content"),"C:\\yhj\\data\\websocketData"+picPath);


            }
            else
            {
                saveMess.setType(mess.getString("type"));
                saveMess.setContent(mess.getString("content"));

            }


            //修改系统时区
            System.setProperty("user.timezone","Asia/Shanghai");

            saveMess.setSendTime(new Date());
            Message.insertSendedMessage(saveMess);

        }else if(User.getUserByID(Integer.valueOf(userID))!=null)
        {
            //接受的用户不在线
//            sendMessageTo("存储消息",this.userID);
            Message saveMess = new Message();
            saveMess.setSenderID(Integer.valueOf(userID));
            System.out.println("hahahhahahahahahhahah:"+saveMess.getSenderID());

            saveMess.setReceiverID(mess.getInt("receiverID"));
            //如果发送图片消息,则不保存图片记录
            if(mess.getString("type").compareTo("图片") == 0)
            {
                //旧版本方案，不存图片
//                saveMess.setType(mess.getString("type"));
//                saveMess.setContent("[该条消息为图片消息]");

                //新版本方案，存图片文件和路径
                saveMess.setType(mess.getString("type"));
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateTime=df.format(new Date());
                String picPath = mess.getInt("receiverID")+mess.getInt("senderID")+dateTime;
                saveMess.setContent(picPath);
                PhotoTools.savaPhoto(mess.getString("content"),"C:\\yhj\\data\\websocketData"+picPath);

            }
            else
            {
                saveMess.setType(mess.getString("type"));
                saveMess.setContent(mess.getString("content"));

            }
//            saveMess.setContent(mess.getString("content"));

            //修改系统时区
            System.setProperty("user.timezone","Asia/Shanghai");

            saveMess.setSendTime(new Date());

            Message.insertMessage(saveMess);
        }

//        sendMessageTo("已收到消息", receiverID);





//        JSONObject jsonTo = JSONObject.fromObject(message);

//        if (!jsonTo.get("To").equals("All")){
//            sendMessageTo("给一个人", jsonTo.get("To").toString());
//        }else{

//        sendMessageTo("发送成功",username);
//        sendMessageAll()
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocketWin item : clients.values()) {
            if (String.valueOf(item.userID).equals(To) )
                item.session.getAsyncRemote().sendText(message);

        }
    }

    public void sendMessageToTong(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocketWin item : clients.values()) {

            if (String.valueOf(item.userID).equals(To) )
                item.session.getBasicRemote().sendText(message);

        }
    }

    public static void closeClient(String uname)
    {
        for (WebSocketWin item : clients.values()) {
            if (String.valueOf(item.userID).equals(uname) )
                try {
                    item.session.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketWin item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAllTong(String message) throws IOException {
        for (WebSocketWin item : clients.values()) {
            item.session.getBasicRemote().sendText(message);
        }
    }



    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketWin.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketWin.onlineCount--;
    }

    public static synchronized Map<String, WebSocketWin> getClients() {
        return clients;
    }


}


//{
//	type:"好友请求";
//	time:"";
//	text:{
//		from:"";
//		to:"";
//	}
//
//}

