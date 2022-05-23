package ua.epam.dao.entities;

import ua.epam.AppContext;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.dao.utils.DaoUserBuilder;
import ua.epam.models.entities.user.IUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<IUser> {

    public static final String ALL_USER = "SELECT * FROM conferences.user_table;";
    public static final String USER_BY_ID = "SELECT * FROM conferences.user_table WHERE user_id = ?;";
    public static final String DELETE_BY_ID = "DELETE FROM conferences.user_table WHERE user_id = ?;";
    public static final String UPDATE_USER_BY_ID = "UPDATE user_table SET conferences.user_table.user_login = ?, \n" +
            "user_table.user_pass = ?, user_table.user_email = ?, user_table.user_name = ?, user_table.user_surname = ? WHERE user_id = ?;";
    public static final String ADD_USER = "INSERT INTO user_table SET conferences.user_table.user_login = ?, " +
            "user_table.user_pass = ?, user_table.user_email = ?, user_table.user_name = ?, " +
            "user_table.user_surname = ?, user_table.user_role = ?;";

    @Override
    public List<IUser> findAll() {
        List<IUser> users = new ArrayList<>();
        try (ResultSet resultSet = ConnectionCreator.createConnection()
                .createStatement().executeQuery(ALL_USER)) {
            while (resultSet.next())
                users.add(DaoUserBuilder.buildUser(resultSet));

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public IUser findEntityById(int id) {
        IUser user;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(USER_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            user = DaoUserBuilder.buildUser(prepareStatement.getResultSet());
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            user = null;
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        boolean statement;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            statement = false;
        }
        return statement;
    }

    @Override
    public boolean delete(IUser entity) {
        return false;
    }

    @Override
    public boolean create(IUser entity) {
        boolean statement = false;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ADD_USER)) {
            prepareStatement.setString(1, entity.getLogin());
            prepareStatement.setString(2, entity.getPassword());
            prepareStatement.setString(3, entity.getEmail());
            prepareStatement.setString(4, entity.getName());
            prepareStatement.setString(5, entity.getSurname());
            prepareStatement.setString(6, entity.getRole().toString());

            prepareStatement.execute();
            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return statement;
    }

    @Override
    public IUser update(IUser entity) {
        IUser user = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(UPDATE_USER_BY_ID)) {
            prepareStatement.setString(1, entity.getLogin());
            prepareStatement.setString(2, entity.getPassword());
            prepareStatement.setString(3, entity.getEmail());
            prepareStatement.setString(4, entity.getName());
            prepareStatement.setString(5, entity.getSurname());
            prepareStatement.setInt(6, entity.getId());
            prepareStatement.execute();

            user = entity;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return user;
    }
}
