package com.soa.kurosz;

import data.Author;
import data.Book;
import service.ILibraryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@ManagedBean(name = "library")
@SessionScoped
public class LibraryController implements Serializable {

    @EJB
    private ILibraryService libraryService;

    @PostConstruct
    public void init() {

        System.out.println("LIBRARY CONTROLLER INIT\n\n\n");
        Author author = new Author();
        author.setName("Author1");
        author.setSurname("Author1S");

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle("TILTE");
        book.setISBN("ISBN");
        libraryService.addBook(book);

        Book book1 = new Book();
        book.setAuthor(author);
        book.setTitle("TILTE2");
        book.setISBN("ISBN22");
        libraryService.addBook(book1);

        Book book2 = new Book();
        book.setTitle("41231");
        book.setISBN("312312");
        libraryService.addBook(book2);
    }

    public String message() {
        return "hELLO hibernate";
    }
}
