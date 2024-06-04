package reConstructor.exception_handling.exceptions.tables;
// свободных столиков нет

import org.springframework.http.HttpStatus;

public class NotFoundFreeDesksException extends RuntimeException {
    private HttpStatus status;

    public NotFoundFreeDesksException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
