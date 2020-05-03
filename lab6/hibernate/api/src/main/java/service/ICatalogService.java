package service;

import data.Author;
import data.Book;

import java.util.List;
import java.util.UUID;

public interface ICatalogService {

    Book addBook(Book book, int totalNumber);

    Book updateBook(Book book);

    List<Book> getBooks();
    Book getBook(UUID bookId);

    Author addAuthor(Author author);
    void updateAuhor(Author author);
    List<Author> getAuthors();
    Author getAuthor(UUID bookId);
}
