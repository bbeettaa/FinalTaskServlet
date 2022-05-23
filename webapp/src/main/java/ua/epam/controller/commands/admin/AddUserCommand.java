package ua.epam.controller.commands.admin;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.DaoUserService;
import ua.epam.dao.UserDao;
import ua.epam.models.entities.IUser;
import ua.epam.utils.user.builder.IBuilder;
import ua.epam.utils.user.builder.UserBuilder;
import ua.epam.utils.user.validator.UserValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class AddUserCommand implements ICommand {
    private final AtomicReference<UserDao> userRepo;

    public AddUserCommand() {
        userRepo = AppContext.USER_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            IBuilder builder = new UserBuilder();
            final IUser user = (IUser) builder.build(req);

            if (UserValidator.validate(user)) {
                userRepo.get().add(user);
                response = ViewPath.FIND_USER_COMMAND;
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}

