package ua.epam;


import org.apache.log4j.Logger;
import ua.epam.dao.UserRepo;

import java.util.concurrent.atomic.AtomicReference;

public class AppContext {
    private AppContext() {
        throw new IllegalStateException("Utility class");
    }

    public static final AtomicReference<UserRepo> USER_REPO = new AtomicReference<>(new UserRepo());
    public static final Logger LOGGER = Logger.getLogger(AppContext.class);
}
