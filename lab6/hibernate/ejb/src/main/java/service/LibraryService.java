package service;

import data.Author;
import data.Book;
import repo.AuthorRepository;
import repo.BookRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ILibraryService.class)
public class LibraryService implements ILibraryService{

    @EJB
    private AuthorRepository authorRepository;

    @EJB
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.create(author);
    }
}
