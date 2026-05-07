package application;

import model.entities.Book;
import model.entities.Loan;
import model.entities.Member;
import model.services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryService service = new LibraryService();

        int option;

        do {

            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1 - Add Book");
            System.out.println("2 - Add Member");
            System.out.println("3 - Perform Loan");
            System.out.println("4 - Return Book");
            System.out.println("5 - List Active Loans");
            System.out.println("0 - Exit");

            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    try {

                        Book book = new Book(title, author, isbn);

                        service.addBook(book);

                        System.out.println("Book added successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Membership ID: ");
                    String memberId = sc.nextLine();

                    try {

                        Member member = new Member(name, email, memberId);

                        service.addMember(member);

                        System.out.println("Member added successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 3:

                    System.out.print("Book ISBN: ");
                    String loanIsbn = sc.nextLine();

                    System.out.print("Member ID: ");
                    String loanMemberId = sc.nextLine();

                    try {

                        service.performLoan(loanIsbn, loanMemberId);

                        System.out.println("Loan performed successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 4:

                    System.out.print("Book ISBN: ");
                    String returnIsbn = sc.nextLine();

                    try {

                        service.returnBook(returnIsbn);

                        System.out.println("Book returned successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 5:

                    List<Loan> activeLoans = service.listActiveLoans();

                    if (activeLoans.isEmpty()) {
                        System.out.println("No active loans.");
                    } else {

                        for (Loan l : activeLoans) {

                            System.out.println("\nBook: " + l.getBook().getTitle());
                            System.out.println("Member: " + l.getMember().getName());
                            System.out.println("Loan Date: " + l.getLoanDate());
                            System.out.println("Expected Return: " + l.getExpectedReturnDate());

                        }

                    }

                    break;

                case 0:
                    System.out.println("Program finished.");
                    break;

                default:
                    System.out.println("Invalid option.");

            }

        } while (option != 0);

        sc.close();

    }

}