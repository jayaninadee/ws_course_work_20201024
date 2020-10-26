package lk.ijse.webservice.resource_access;

import lk.ijse.webservice.resource_access.api.ResourceAccessRest;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.Serializable;

public class RestServer {
    private Server server;


    public void start() throws Exception {
//        Server server = new Server(8080);
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//
//        context.addServlet(ResourceAccessRest.class, "/login");
//        context.addServlet(ResourceAccessRest.class, "/login2");
//        server.setHandler(context);

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});

        //Specify the Session ID Manager

        SessionIdManager idManager =new DefaultSessionIdManager(server);
        server.setSessionIdManager(idManager);

        SessionHandler sessions = new SessionHandler();
        sessions.setSessionIdManager(idManager);

        ServletHandler serverletHandler = new ServletHandler();
        sessions.setHandler(serverletHandler);

        serverletHandler.addServletWithMapping(ResourceAccessRest.class,"/connect");
        serverletHandler.addServletWithMapping(ResourceAccessRest.class,"/send");
        serverletHandler.addServletWithMapping(ResourceAccessRest.class,"/recive");

        server.start();
        server.join();
    }
}