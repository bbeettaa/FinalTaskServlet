package ua.epam.utils;

import ua.epam.models.entities.IUser;
import ua.epam.models.Role;
import ua.epam.models.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    public static List<IUser> createTestUsers() {
        List<IUser> users = new ArrayList<>();

        users.add(new User(0,"a","1","ad","min","admin@admin.admin",Role.ADMIN));
        users.add(new User(1,"m1","1","m1","m11","mn@admin.admin",Role.MODERATOR));
        users.add(new User(2,"s1","1","s","s","s@admin.admin",Role.SPEAKER));
        users.add(new User(3,"u1","1","u","u","u@u.u",Role.USER));

        return users;
    }

    public static IUser createUser() {
        User user = new User();
        // user.setId(id);
        // user.setName(name);
        // user.setAge(age);
        return user;
    }


}
