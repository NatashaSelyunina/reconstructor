package reConstructor.exception_handling.exceptions.common_classes;
// класс для исключений при ошибке получения всех активных сущностей

import org.springframework.http.HttpStatus;

public class NotFoundActiveEntitiesException extends RuntimeException {
    private HttpStatus status;

    public NotFoundActiveEntitiesException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
