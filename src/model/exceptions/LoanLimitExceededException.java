package model.exceptions;

public class LoanLimitExceededException extends RuntimeException {
//    Exception responsavel por controlar a quantidade de emprestimo por cada membro
//    lança um erro caso já tenha atingido o limite de 3 emprestimo

    public LoanLimitExceededException(String memberId) {
        super("Membro: " + memberId + " já atingiu o limite de 3 emprestimos.");
    }

}
