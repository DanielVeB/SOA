package service;

import data.Book;
import data.Catalog;

import javax.ejb.Remote;

@Remote
public interface ILibraryService {

    Catalog borrowBook(Book book);

}
