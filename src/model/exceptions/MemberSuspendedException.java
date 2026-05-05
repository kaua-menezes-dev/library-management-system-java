package model.exceptions;

public class MemberSuspendedException extends RuntimeException {
//    Exception responsavel por lançar um erro caso o membro exceda em 30 dias a devolução de um livro.

    public MemberSuspendedException(String name) {
        super("Membro: " + name + " está suspenso e não pode realizar empréstimos.");
    }

}
