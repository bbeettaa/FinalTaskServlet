package ua.epam.dao.utils;

import ua.epam.AppContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConnectionCreator {
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASS;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        DATABASE_URL = rb.getString("db.url");
        DATABASE_USER = rb.getString("db.user");
        DATABASE_PASS = rb.getString("db.password");
    }

    private ConnectionCreator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @registering the jdbc driver here
     */
    public static Connection createConnection() throws SQLException {
        Connection connect;
        Statement statement;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/conferences?user=root&password=root");
            statement = connect.createStatement();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return statement.getConnection();

        //return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        //return DriverManager.getConnection("jdbc:mysql://localhost/conferences?user=root&password=root").createStatement().getConnection();
/*        Connection con=null;
        try {
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return con;*/
    }

}

