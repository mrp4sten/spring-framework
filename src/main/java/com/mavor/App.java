package com.mavor;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.mavor.config.AppConfig;

public class App {
  public static void main(String[] args) throws Exception {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);

    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

    ServletHolder servletHolder = new ServletHolder(dispatcherServlet);

    ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    contextHandler.setContextPath("/");
    contextHandler.addServlet(servletHolder, "/*");

    Server server = new Server(8080);
    server.setHandler(contextHandler);

    server.start();
    server.join();
  }
}