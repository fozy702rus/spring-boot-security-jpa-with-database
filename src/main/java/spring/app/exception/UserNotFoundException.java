package spring.app.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("Причина ошибки: пользователь с ID: " + id + " - не существует");
    }
}