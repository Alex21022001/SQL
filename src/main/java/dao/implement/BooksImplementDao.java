package dao.implement;

import dao.BaseDao;
import dao.DaoException;
import entity.Books;
import org.w3c.dom.DOMException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksImplementDao implements BaseDao<Books> {
    private static final String SQL_SELECT_ALL_BOOKS = "SELECT b_id,b_name,b_year,b_quantity FROM books";
    private static final String SQL_SELECT_BOOKS_BY_ID = "SELECT * FROM books WHERE b_id =";
    private static final String SQL_SELECT_BOOKS_BY_NAME = "SELECT * FROM books WHERE b_name =";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM books WHERE b_id=";

    @Override
    public List<Books> viewALl() throws DaoException {
        List<Books> listOfBooks = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getInt("b_id"));
                books.setTitle(resultSet.getString("b_name"));
                books.setYear(resultSet.getInt("b_year"));
                books.setQuantity(resultSet.getInt("b_quantity"));
                listOfBooks.add(books);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfBooks;
    }

    @Override
    public boolean update(Books books) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                if (books.getId() == resultSet.getInt("b_id")) {
                    resultSet.updateString("b_name", books.getTitle());
                    resultSet.updateInt("b_year", books.getYear());
                    resultSet.updateInt("b_quantity", books.getQuantity());
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
    public List<Books> findById(String id) throws DaoException {
        List<Books> listOfBooks = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BOOKS_BY_ID + id);
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getInt("b_id"));
                books.setTitle(resultSet.getString("b_name"));
                books.setYear(resultSet.getInt("b_year"));
                books.setQuantity(resultSet.getInt("b_quantity"));
                listOfBooks.add(books);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfBooks;
    }

    @Override
    public List<Books> findByName(String name) throws DaoException {
        List<Books> listOfBooks = new ArrayList<>();
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BOOKS_BY_NAME + name);
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getInt("b_id"));
                books.setTitle(resultSet.getString("b_name"));
                books.setYear(resultSet.getInt("b_year"));
                books.setQuantity(resultSet.getInt("b_quantity"));
                listOfBooks.add(books);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return listOfBooks;
    }

    @Override
    public boolean deleteById(String id) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(SQL_DELETE_BY_ID + id);
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
    public boolean createNewRow(Books books) throws DaoException {
        Connection connection = connection();
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            resultSet.moveToInsertRow();
            resultSet.updateInt("b_id", books.getId());
            resultSet.updateString("b_name", books.getTitle());
            resultSet.updateInt("b_year", books.getYear());
            resultSet.updateInt("b_quantity", books.getQuantity());
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
