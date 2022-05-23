package ua.epam;

import java.sql.*;

public class Main {
    private static Connection connect = null;
    private static Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/conferences?"
                        + "user=root&password=root");

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query


    }
}
