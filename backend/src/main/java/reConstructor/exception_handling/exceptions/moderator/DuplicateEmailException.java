package reConstructor.exception_handling.exceptions.moderator;

import org.springframework.http.HttpStatus;
// такой email уже существует

public class DuplicateEmailException extends RuntimeException {
    private HttpStatus status;

    public DuplicateEmailException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
