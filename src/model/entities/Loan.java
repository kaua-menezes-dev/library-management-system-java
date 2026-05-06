package model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {

    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private Double fee;
    private Book book;
    private Member member;

    public Loan() {
    }

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = LocalDate.now();
        this.expectedReturnDate = loanDate.plusDays(14);
        this.fee = 0.0;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public Double getFee() {
        return fee;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public void calculatedFee() {

        long days = ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);

        if (days >= 14) {
            this.fee = days * 2.0;
        }

    }

    public boolean isLate() {

        if (actualReturnDate == null) {
            return false;
        }

        return actualReturnDate.isAfter(expectedReturnDate);

    }

    public long getDelayDays() {

        if (actualReturnDate == null || !isLate()) {
            return 0;
        }

        return ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);

    }

    public boolean isReturned() {
        return actualReturnDate != null;
    }

    public boolean isActive() {
        return !isReturned();
    }

}
