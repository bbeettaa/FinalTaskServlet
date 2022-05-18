package ua.epam.servlets;

import org.apache.log4j.PropertyConfigurator;
import ua.epam.LoggingHelper;
import ua.epam.dao.UserDAO;
import ua.epam.models.IUser;
import ua.epam.users.utils.UsersUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
//import java.util.logging.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> dao;
    private static final Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dao = new AtomicReference<>(new UserDAO());
        for (IUser user : UsersUtils.createTestUsers())
            dao.get().add(user);

        //PropertyConfigurator.configure("log4j.properties");
/*        LoggingHelper obj = new LoggingHelper();
        obj.log();*/
        //logger.warn("ApiServlet.init");

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
        //logger.info("hello");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }

}
