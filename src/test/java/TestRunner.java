import bookstable.Books;
import bookstable.BooksTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.concurrent.TimeUnit;

public class TestRunner {
    @Test
    void test(){
        BooksTable.checkBooksTable();
    }
    @Test
    void testUpdateBooksTable(){
        BooksTable.updateBooksTable(8,"Sakura",2007,7);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "details.csv")
    void testInsertIntoBooksTable(int id,String title,int year,int quantity){
        BooksTable.insertIntoBooksTable(id,title,year,quantity);
    }
    @Test
    void testInsert(){
        Assertions.assertTrue( BooksTable.insert());
    }
    @Test
    void testDeleteOnTitle(){
        BooksTable.deleteOnTitle("'Naruto'");
    }

}
