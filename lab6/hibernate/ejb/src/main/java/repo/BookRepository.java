package repo;

import data.Book;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;

import static javax.ejb.TransactionManagementType.BEAN;

@Stateless
@TransactionManagement(BEAN)
public class BookRepository extends AbstractRepo<Book> {

    @Override
    protected Class<Book> getType() {
        return Book.class;
    }

}
