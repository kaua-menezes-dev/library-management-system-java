package model.exceptions;

public class InvalidIsbnException extends RuntimeException {
//    Exception responsavel por controlar a quantidade de digitos do ISBN
//    laça um erro caso o ISBN não tiver 13 digitos.

    public InvalidIsbnException(String isbn) {
        super("ISBN inválido: " + isbn + ". Deve conter exatamente 13 dígitos.");
    }


}
