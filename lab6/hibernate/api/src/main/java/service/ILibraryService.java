package service;

import data.Author;
import data.Book;

import javax.ejb.Remote;

@Remote
public interface ILibraryService {

    Book addBook(Book book);
    Author addAuthor(Author author);

}
