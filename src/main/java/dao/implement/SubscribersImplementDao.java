package dao.implement;

import dao.BaseDao;
import dao.DaoException;
import entity.Books;
import entity.Subscribers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscribersImplementDao implements BaseDao<Subscribers> {
    private static final String SQL_SELECT_ALL_SUBSCRIBERS = "SELECT s_id,s_name FROM subscribers";
    private static final String SQL_SELECT_SUBSCRIBERS_BY_ID = "SELECT s_id,s_name FROM subscribers WHERE s_id=";
    private static final String SQL_SELECT_SUBSCRIBERS_BY_NAME = "SELECT s_id,s_name FROM subscribers WHERE s_name=";
    private static final String SQL_DELETE_SUBSCRIBERS_BY_ID = "DELETE FROM subscribers WHERE s_id=";

    @Override
    public List<Subscribers> viewALl() throws DaoException {
        List<Subscribers> listOfSubscribers = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SUBSCRIBERS);
            while (resultSet.next()) {
                Subscribers subscribers = new Subscribers();
                subscribers.setId(resultSet.getInt("s_id"));
                subscribers.setName(resultSet.getString("s_name"));
                listOfSubscribers.add(subscribers);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfSubscribers;
    }

    @Override
    public boolean update(Subscribers subscribers) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SUBSCRIBERS);
            while (resultSet.next()) {
                if (subscribers.getId() == resultSet.getInt("s_id")) {
                    resultSet.updateString("s_name", subscribers.getName());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(statement);
        }
        return true;
    }

    @Override
    public List<Subscribers> findById(String id) throws DaoException {
        List<Subscribers> listOfSubscribers = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_SUBSCRIBERS_BY_ID + id);
            while (resultSet.next()) {
                Subscribers subscribers = new Subscribers();
                subscribers.setId(resultSet.getInt("s_id"));
                subscribers.setName(resultSet.getString("s_name"));
                listOfSubscribers.add(subscribers);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfSubscribers;
    }

    @Override
    public List<Subscribers> findByName(String name) throws DaoException {
        List<Subscribers> listOfSubscribers = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_SUBSCRIBERS_BY_NAME + name);
            while (resultSet.next()) {
               Subscribers subscribers = new Subscribers();
               subscribers.setId(resultSet.getInt("s_id"));
               subscribers.setName(resultSet.getString("s_name"));
               listOfSubscribers.add(subscribers);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfSubscribers;
    }

    @Override
    public boolean deleteById(String id) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(SQL_DELETE_SUBSCRIBERS_BY_ID + id);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                throw new DaoException(ex);
            } finally {
                try {
                    connection.setAutoCommit(true);
                    close(connection);
                    close(statement);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    throw new DaoException(ex);
                }
            }
        }
        return true;
    }

    @Override
    public boolean createNewRow(Subscribers subscribers) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SUBSCRIBERS);
            resultSet.moveToInsertRow();
            resultSet.updateInt("s_id", subscribers.getId());
            resultSet.updateString("s_name", subscribers.getName());
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(statement);
        }
        return true;
    }
}
