package chatExample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.out;
import java.net.Socket;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import modelo.mensaje;

@ServerEndpoint("/chat")
public class ChatServlet {

    private static Set<Session> userSessions = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());
    
    @OnOpen
    public void onOpen(Session curSession) {
        userSessions.add(curSession);
    }

    @OnClose
    public void onClose(Session curSession) {
        userSessions.remove(curSession);
    }

    @OnMessage
    public void onMessage(String message, Session userSession){
        try {
            Socket socket = new Socket("192.168.100.13", 9998);
            ObjectOutputStream objeto = new ObjectOutputStream(socket.getOutputStream());
            
            String[] pack = message.split("#");
            
            mensaje paquete = new mensaje(pack[0], pack[1], "0", null);
            objeto.writeObject(paquete);
            socket.close();

            for (Session ses : userSessions) {
                String mostrar = paquete.getUsuario() + ": " + paquete.getMensaje();
                ses.getAsyncRemote().sendText(mostrar);
            }

        } catch (IOException e) {
               
                userSession.getAsyncRemote().sendText("Error al conectar con el Servidor del chat no se pudo mandar el mensaje");
                
            System.out.println("Error: " + e);
        }

    }

    @OnError
    public void onError(Throwable t) {
    }
}
