package model.services;

import model.entities.Book;
import model.entities.Loan;
import model.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }
}
