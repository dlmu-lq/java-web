package top.itlq.java.web.websocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id){
        System.out.println("新连接：" + id);
    }
}
