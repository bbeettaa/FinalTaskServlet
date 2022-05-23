package ua.epam.controller.commands.admin;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.UserDao;
import ua.epam.models.entities.IUser;
import ua.epam.utils.user.builder.IBuilder;
import ua.epam.utils.user.builder.UserBuilder;
import ua.epam.utils.user.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateUserCommand implements ICommand {
    private final AtomicReference<UserDao> userRepo;

    public UpdateUserCommand() {
        userRepo = AppContext.USER_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            final String id = req.getParameter("id");
            //final IUser oldUser = userRepo.get().getById(Integer.parseInt(id));
            IBuilder builder = new UserBuilder();
            final IUser user = (IUser) builder.build(req);
            user.setId(Integer.parseInt(id));

            if (UserValidator.validate(user)) {
                userRepo.get().update(user);
                //req.setAttribute();
                response = ViewPath.ALL_USERS_COMMAND;
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}