package foo;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{collection}")
public class Ws {

    Session ws;
    String coll;

    static ConcurrentHashMap<String, Set<Session>> conns = new ConcurrentHashMap<>(); //sessions per user name


    public void broadcast(String reply) throws IOException {

        for (Session s : ws.getOpenSessions())
            if (!s.equals(ws))
                s.getBasicRemote().sendText(reply);


    }


    @OnOpen
    public void onOpen(Session session, EndpointConfig c,
                       @PathParam("collection") String collname ) throws IOException, ParseException {
        //session.setMaxIdleTimeout(0);
        this.ws = session;
        this.coll=collname;
        if (!conns.containsKey(collname))
            conns.put(collname, new HashSet<Session>(){{this.add(ws);}});
        else
            conns.get(collname).add(session);
    }

    @OnClose
    public void onClose(Session session) {
        conns.get(coll).remove(session);
    }

    @OnMessage
    public String onMessage(String message) {

        try {
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;


    }



}
