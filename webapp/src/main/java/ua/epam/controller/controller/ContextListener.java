package ua.epam.controller.controller;

import ua.epam.AppContext;
import ua.epam.dao.UserDao;
import ua.epam.models.entities.IUser;
import ua.epam.utils.UsersUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicReference;
//import java.util.logging.*;


public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDao> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dao = AppContext.USER_REPO;
        for (IUser user : UsersUtils.createTestUsers())
            dao.get().add(user);

        //PropertyConfigurator.configure("log4j.properties");
/*        LoggingHelper obj = new LoggingHelper();
        obj.log();*/
        //logger.warn("ApiServlet.init");

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
        AppContext.LOGGER.info("hello");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }

}
