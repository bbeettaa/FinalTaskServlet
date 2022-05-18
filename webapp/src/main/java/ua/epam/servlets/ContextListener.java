package ua.epam.servlets;

import ua.epam.dao.UserDAO;
import ua.epam.models.Role;
import ua.epam.models.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.*;

public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> dao;
    public static Logger logger;
    static {
        try {
            logger = Logger.getLogger(ContextListener.class.getSimpleName());
            Handler fh = new FileHandler("prog.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dao = new AtomicReference<>(new UserDAO());

        dao.get().add(new User(1, "a", "1","email@gmail.com", Role.ADMIN));
        dao.get().add(new User(2, "m1", "1","email@gmail.com", Role.MODERATOR));
        dao.get().add(new User(3, "s1", "1","email@gmail.com", Role.SPEAKER));
        dao.get().add(new User(4, "u1", "1","email@gmail.com", Role.USER));

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao=null;
    }
}
