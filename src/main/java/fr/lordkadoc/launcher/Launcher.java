package fr.lordkadoc.launcher;
import java.net.InetSocketAddress;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
	
public class Launcher {

    public static void main(String[] args) throws Exception {
        Server server = new Server(new InetSocketAddress("192.168.1.40", 9876));
        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(EndPoint.class);
            }
        };
        server.setHandler(wsHandler);
        server.start();
        server.join();
    }
} 