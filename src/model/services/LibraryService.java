package model.services;

import model.entities.Book;
import model.entities.Loan;
import model.entities.Member;
import model.enums.BookStatus;
import model.enums.MemberStatus;
import model.exceptions.BookNotAvailableException;
import model.exceptions.InvalidIsbnException;
import model.exceptions.LoanLimitExceededException;
import model.exceptions.MemberSuspendedException;

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


    private Book findBook(String isbn) {

        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }

        throw new RuntimeException("ISBN não encontrado");

    }

    private Member findMember(String memberId) {

        for (Member m : members) {
            if (m.getMembershipId().equals(memberId)) {
                return m;
            }
        }

        throw new RuntimeException("Id do membro não foi encontrado.");

    }

    private void validateBook(Book book) {

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new BookNotAvailableException(book.getTitle());
        }

    }

    private void validateIsbn(String isbn) {

        if (!isbn.matches("\\d{13}")) {
            throw new InvalidIsbnException(isbn);
        }

    }

    private void validateMemberStatus(Member member) {

        if (member.getMemberStatus() != MemberStatus.ACTIVE) {
            throw new MemberSuspendedException(member.getName());
        }

    }

    private void validateLoanLimit(Member member) {

        int activeLoans = countActiveLoans(member);

        if (activeLoans >= 3) {
            throw new LoanLimitExceededException(member.getMembershipId());
        }

    }

    private int countActiveLoans(Member member) {

        int count = 0;

        for (Loan l : loans) {
            if (l.getMember().equals(member) && l.isActive()) {
                count++;
            }
        }

        return count;

    }

}
