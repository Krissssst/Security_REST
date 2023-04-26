package spring.boot_security.exeption_handling;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String massage) {
        super(massage);
    }

}
