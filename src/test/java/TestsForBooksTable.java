import dao.DaoException;
import dao.implement.BooksImplementDao;
import entity.Books;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TestsForBooksTable {
    @Test
    void viewAllBooks() throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        System.out.println(booksImplementDao.viewALl());
    }

    @Test
    void viewBooksById() throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        System.out.println(booksImplementDao.findById("5"));
    }

    @Test
    void viewBooksByTitle() throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        System.out.println(booksImplementDao.findByName("'Eugene Onegin'"));
    }

    @Test
    void deleteBookById() throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        Assertions.assertTrue(booksImplementDao.deleteById("8"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "books/dataforcreate.csv")
    void createNewRow(int id,String title,int year,int quantity) throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        Books books = new Books();
        books.setId(id);
        books.setTitle(title);
        books.setYear(year);
        books.setQuantity(quantity);
        Assertions.assertTrue(booksImplementDao.createNewRow(books));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "books/dataforupdate.csv")
    void updateBooksRow(int id,String title,int year,int quantity) throws DaoException {
        BooksImplementDao booksImplementDao = new BooksImplementDao();
        Books books = new Books();
        books.setId(id);
        books.setTitle(title);
        books.setYear(year);
        books.setQuantity(quantity);
        Assertions.assertTrue(booksImplementDao.update(books));
    }
}
