//package com.myself.computerThinking.WebScoket;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CopyOnWriteArraySet;
//
///**
// * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
// * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
// */
//@ServerEndpoint("/websocket/{sid}")
//@Component     //此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
//public class WebSocketServer {
//
//    static Logger log= LoggerFactory.getLogger(WebSocketServer.class);
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    //private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
//
//    private static ConcurrentHashMap<String, ArrayList<WebSocketServer>> webSocketServers = new ConcurrentHashMap<>();
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    //接收sid
//    private String sid="";
//
//    /**
//     * 建立连接成功的方法
//     * @param session
//     * @param sid
//     */
//    @OnOpen
//    public void onOpen(Session session,@PathParam("sid") String sid) {
//        this.session = session;
////        webSocketSet.add(this);     //加入set中
//
//        ArrayList<WebSocketServer> list=webSocketServers.get(sid);
//        if(list==null){
//            list = new ArrayList<>();
//            webSocketServers.put(sid,list);
//        }
//        list.add(this);
//        addOnlineCount();           //在线数加1
//        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
//        this.sid=sid;
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("websocket IO异常");
//        }
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose() {
////        webSocketSet.remove(this);  //从set中删除
//        if(webSocketServers.get(this.sid)!=null){
//            webSocketServers.get(this.sid).remove(this);
//            subOnlineCount();           //在线数减1
//            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
//        }
//
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     * @param message 客户端发送过来的消息
//     *
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        log.info("收到来自窗口"+sid+"的信息:"+message);
//            try {
//                this.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        //群发消息
//        /*for (WebSocketServer item : webSocketServers) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }*/
//    }
//
//    /**
//     *
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
//        error.printStackTrace();
//    }
//
//    /**
//     * 实现服务器主动推送
//     */
//    public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
//    }
//
//
//    /**
//     * 群发自定义消息
//     * */
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
//        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
//        ArrayList<WebSocketServer> webSocketServerList =webSocketServers.get(sid);
//        if(webSocketServerList!=null){
//            for (WebSocketServer webSocketServer:webSocketServerList){
//                webSocketServer.sendMessage(message);
//            }
//        }
//        /*for (WebSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }*/
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocketServer.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//    }
//}
