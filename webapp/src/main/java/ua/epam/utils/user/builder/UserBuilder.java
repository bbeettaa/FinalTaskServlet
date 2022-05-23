package ua.epam.utils.user.builder;

import ua.epam.AppContext;
import ua.epam.models.Role;
import ua.epam.models.entities.user.IUser;
import ua.epam.models.entities.user.User;

import javax.servlet.http.HttpServletRequest;

public class UserBuilder implements IBuilder {

    @Override
    public Object build(HttpServletRequest req) {
        IUser user = new User();

        try {
            user.setLogin(req.getParameter("loginToSet"));
            user.setPassword(req.getParameter("passwordToSet"));
            String roleStr = req.getParameter("roleToSet").toUpperCase();
            user.setRole(Enum.valueOf(Role.UNREGISTERED.getDeclaringClass(), roleStr));
            user.setEmail(req.getParameter("emailToSet"));
            user.setName(req.getParameter("nameToSet"));
            user.setSurname(req.getParameter("surnameToSet"));
        }catch (NullPointerException e){
            AppContext.LOGGER.error(e.getMessage());
        }
        return user;
    }
}
