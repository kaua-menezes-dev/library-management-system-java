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

import java.time.LocalDate;
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

    public void performLoan(String isbn, String memberId){

        Book book = findBook(isbn);
        Member member = findMember(memberId);

        validateBook(book);
        validateMemberStatus(member);
        validateLoanLimit(member);

        Loan loan = new Loan(book, member);

        book.setStatus(BookStatus.BORROWED);
        loans.add(loan);

    }

    public Book findBookByIsbn(String isbn) {
        return findBook(isbn);
    }

    public Member findMemberById(String memberId) {
        return findMember(memberId);
    }

    public Loan findActiveLoanByBook(String isbn) {

        for (Loan l : loans) {
            if (l.getBook().getIsbn().equals(isbn) && l.isActive()) {
                return l;
            }
        }

        return null;

    }

    public int countActiveLoans(Member member) {

        int count = 0;

        for (Loan l : loans) {
            if (l.getMember().equals(member) && l.isActive()) {
                count++;
            }
        }

        return count;

    }

    public boolean hasActiveLoan(Book book) {

        for (Loan l : loans) {
            if (l.getBook().equals(book) && l.isActive()) {
                return true;
            }
        }

        return false;

    }

    public List<Loan> listActiveLoans() {

        List<Loan> activeLoans = new ArrayList<>();

        for (Loan l : loans) {
            if (l.isActive()) {
                activeLoans.add(l);
            }
        }

        return activeLoans;

    }

    public void returnBook(String isbn) {

        Loan loan = findActiveLoanByBook(isbn);

        if (loan == null) {
            throw new RuntimeException("Nenhum empréstimo ativo encontrado para este livro");
        }

        loan.setActualReturnDate(LocalDate.now());

        loan.calculatedFee();

        if (loan.getDelayDays() >= 30) {
            loan.getMember().setMemberStatus(MemberStatus.SUSPENDED);
        }

        loan.getBook().setStatus(BookStatus.AVAILABLE);

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

}
