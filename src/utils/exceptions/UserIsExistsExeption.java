package utils.exceptions;

public class UserIsExistsExeption extends RuntimeException {
    public UserIsExistsExeption(String message) {
        super(message);
    }
}
