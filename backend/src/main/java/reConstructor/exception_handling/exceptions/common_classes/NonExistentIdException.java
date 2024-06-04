package reConstructor.exception_handling.exceptions.common_classes;
// класс для исключений при ошибке нахождения сущности по id сущности

import org.springframework.http.HttpStatus;

public class NonExistentIdException extends RuntimeException {
    private HttpStatus status;

    public NonExistentIdException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
