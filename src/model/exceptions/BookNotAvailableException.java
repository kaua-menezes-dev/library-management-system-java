package model.exceptions;

public class BookNotAvailableException extends RuntimeException {
//    Exception responsavel por controlar a disponibilidade de um livro
//    lança um erro caso o livro não esteja disponivel

    public BookNotAvailableException(String title) {
        super("Livro: " + title + " não está disponível para empréstimo.");
    }

}
