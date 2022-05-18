package ua.epam.users.utils;

import ua.epam.models.IUser;
import ua.epam.models.Role;
import ua.epam.models.User;
import ua.epam.users.utils.user.builder.builder.IBuilder;
import ua.epam.users.utils.user.builder.builder.UserBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        IBuilder builder = new UserBuilder();
        builder.buildId("0").buildLogin("admin").buildPassword("12345").buildRole("ADMIN");
        users.add(builder.getResult());
        builder.buildId("1").buildLogin("moder1").buildPassword("12345").buildRole("MODERATOR");
        users.add(builder.getResult());
        builder.buildId("2").buildLogin("speaker1").buildPassword("12345").buildRole("SPEAKER");
        users.add(builder.getResult());
        builder.buildId("3").buildLogin("user1").buildPassword("12345").buildRole("USER");
        users.add(builder.getResult());
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
