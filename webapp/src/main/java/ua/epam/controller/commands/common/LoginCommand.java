package ua.epam.controller.commands.common;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.IUser;
import ua.epam.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // error handler
        String errorMessage;
        String forward = ViewPath.LOGIN_MENU;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            req.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        IUser user = AppContext.USER_REPO.get().getUserByLoginPassword(login,password);
        if (user.getLogin() == null ){
            errorMessage = "Cannot find user with such login or password";
            req.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role userRole = user.getRole();

            if (userRole == Role.ADMIN) {
                req.setAttribute("dao", AppContext.USER_REPO.get().getAll());
                forward = ViewPath.ADMIN_MENU;
            }
            else if (userRole == Role.USER) {
                forward = ViewPath.USER_MENU;
            }

            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
        }
        return forward;
    }
}
