package ua.epam.users.utils;

import ua.epam.models.User;

import javax.servlet.http.HttpServletRequest;

public class UsersUtils {

    private UsersUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null &&
                (id.length() > 0) &&
                id.matches("[+]?\\d+");
    }

    public static User createUser() {
        User user = new User();
       // user.setId(id);
       // user.setName(name);
       // user.setAge(age);
        return user;
    }


}
