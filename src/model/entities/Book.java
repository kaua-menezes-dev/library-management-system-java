package model.entities;

import model.enums.BookStatus;
import model.exceptions.InvalidIsbnException;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private BookStatus status;

    public Book(){}

    public Book(String title, String author, String isbn) {

        if(!isbn.matches("\\d{11}")){
            throw new InvalidIsbnException(isbn);
        }

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = BookStatus.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public boolean isAvailable(){
        return status == BookStatus.AVAILABLE;
    }
}
