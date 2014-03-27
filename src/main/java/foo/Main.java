package foo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

//not used
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Enable javax.websocket configuration for the context
        ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(context);

        // Add your websockets to the container
        wsContainer.addEndpoint(Ws.class);

        //context.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class,"/");
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        server.setHandler(webapp);

        context.addServlet(new ServletHolder(new Ping()),"/ping");

        server.start();
        context.dumpStdErr();
        server.join();
    }
}
