package dao;

import entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public interface BaseDao<T extends Entity> {
    Logger logger = LogManager.getRootLogger();
    List<T> viewALl() throws DaoException;
    boolean update(T t) throws DaoException;
    List<T> findById(String id) throws DaoException;
     List<T> findByName(String name) throws DaoException;
    boolean deleteById(String id) throws DaoException;
    boolean createNewRow(T t) throws DaoException;
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }
    default Connection connection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "qweasdzxc2102");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    default void close(Connection connection){
        try {
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }
}
