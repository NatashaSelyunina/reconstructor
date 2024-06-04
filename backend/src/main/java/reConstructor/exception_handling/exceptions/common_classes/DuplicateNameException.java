package reConstructor.exception_handling.exceptions.common_classes;

import org.springframework.http.HttpStatus;

public class DuplicateNameException extends RuntimeException {
    private HttpStatus status;

    public DuplicateNameException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
