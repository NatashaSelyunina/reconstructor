package reConstructor.exception_handling.exceptions.common_classes;
// класс для сущностей (удаление, восстановление, поиск) по названию

import org.springframework.http.HttpStatus;

public class NonExistentNameException extends RuntimeException {
    private HttpStatus status;

    public NonExistentNameException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
