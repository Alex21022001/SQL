package bookstable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksTable extends BaseConnection {

    public static void checkBooksTable() {
        getConnection();
        ResultSet resultSet = getResultSet();
        List<Books> books = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int year = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);
                books.add(new Books(id, title, year, quantity));
            }
        } catch (SQLException e) {
        }
        closeConnection();
        System.out.println(books);
    }

    public static void updateBooksTable(int id, String title, int year, int quantity) {
        getConnection();
        ResultSet resultSet = getResultSet();
        try {
            while (resultSet.next()) {
                int originId = resultSet.getInt(1);
                if (originId == id) {
                    resultSet.updateString(2, title);
                    resultSet.updateInt(3, year);
                    resultSet.updateInt(4, quantity);
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            closeConnection();
        }
    }
    public static boolean insert(){
        Connection connection = null;
        try {
          connection=  getConnection();
          connection.setAutoCommit(false);
            Statement statement = getStatement();
            statement.addBatch("INSERT INTO books VALUES(13,'Jek',2012,8)");
            statement.addBatch("INSERT INTO books VALUES(14,'Jek',2012,8)");
            statement.addBatch("INSERT INTO books VALUES(15,'Jek',2012,8)");
        int[]arr=  statement.executeBatch();
        for (int el:arr){
            if (el!=1){
                return false;
            }
        }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection!=null){
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);
                    closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
    public static void insertIntoBooksTable(int id, String title, int year, int quantity){
        getConnection();
        ResultSet resultSet = getResultSet();
        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt(1,id);
            resultSet.updateString(2, title);
            resultSet.updateInt(3, year);
            resultSet.updateInt(4, quantity);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void deleteOnID(int id){
        Connection connection = null;
        try {
            connection=  getConnection();
            connection.setAutoCommit(false);
            Statement statement = getStatement();
            statement.addBatch("DELETE FROM books WHERE b_id="+id);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection!=null){
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);
                    closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void deleteOnTitle(String title){
        Connection connection = null;
        try {
            connection=  getConnection();
            connection.setAutoCommit(false);
            Statement statement = getStatement();
            statement.addBatch("DELETE FROM books WHERE b_name="+title);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection!=null){
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);
                    closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
