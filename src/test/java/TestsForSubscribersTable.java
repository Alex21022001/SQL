import dao.DaoException;
import dao.implement.BooksImplementDao;
import dao.implement.SubscribersImplementDao;
import entity.Books;
import entity.Subscribers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TestsForSubscribersTable {
    @Test
    void viewAllSubscribers() throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        System.out.println(sub.viewALl());
    }

    @Test
    void viewSubscribersById() throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        System.out.println(sub.findById("4"));
    }

    @Test
    void viewSubscribersByName() throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        System.out.println(sub.findByName("'Sidorov S.S.'"));
    }

    @Test
    void deleteBookById() throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        Assertions.assertTrue(sub.deleteById("5"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "subscribers/dataforcreate.csv")
    void createNewRow(int id, String name) throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        Subscribers subscribers = new Subscribers();
        subscribers.setId(id);
        subscribers.setName(name);
        Assertions.assertTrue(sub.createNewRow(subscribers));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "subscribers/dataforupdate.csv")
    void updateBooksRow(int id, String name) throws DaoException {
        SubscribersImplementDao sub = new SubscribersImplementDao();
        Subscribers subscribers = new Subscribers();
        subscribers.setId(id);
        subscribers.setName(name);
        Assertions.assertTrue(sub.update(subscribers));
    }
}
