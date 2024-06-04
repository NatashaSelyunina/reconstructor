package reConstructor.exception_handling.exceptions.access;
// недостаточно прав

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends RuntimeException {
    private HttpStatus status;

    public UnauthorizedAccessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
