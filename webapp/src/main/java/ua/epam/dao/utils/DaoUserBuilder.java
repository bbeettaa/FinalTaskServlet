package ua.epam.dao.utils;

import ua.epam.AppContext;
import ua.epam.models.Role;
import ua.epam.models.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUserBuilder {
    public static User buildUser( ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("user_login"));
            user.setPassword(resultSet.getString("user_pass"));
            user.setEmail(resultSet.getString("user_email"));
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("user_surname"));

            String roleStr = resultSet.getString("user_role");
            user.setRole(Enum.valueOf(Role.UNREGISTERED.getDeclaringClass(), roleStr));

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
return user;
    }
}
