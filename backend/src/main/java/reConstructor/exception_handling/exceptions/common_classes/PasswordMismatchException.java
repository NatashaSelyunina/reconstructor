package reConstructor.exception_handling.exceptions.common_classes;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends RuntimeException {

    private HttpStatus status;

    public PasswordMismatchException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
