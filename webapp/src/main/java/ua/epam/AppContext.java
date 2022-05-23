package ua.epam;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.dao.UserDao;

import java.util.concurrent.atomic.AtomicReference;

public class AppContext {
    private AppContext() {
        throw new IllegalStateException("Utility class");
    }

    public static final AtomicReference<UserDao> USER_REPO = new AtomicReference<>(new UserDao());
    public static final Logger LOGGER = LogManager.getLogger(AppContext.class);
}
