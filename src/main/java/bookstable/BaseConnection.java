package bookstable;

import java.sql.*;

public class BaseConnection {
    private static Connection connection;

    public static Statement getStatement() {
        return statement;
    }

    private static Statement statement;

    public static ResultSet getResultSet() {
        return resultSet;
    }

    private static ResultSet resultSet;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "qweasdzxc2102");
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM books");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
