package reConstructor.exception_handling.exceptions.access.jwt;
// other cases

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RuntimeException{
    private HttpStatus status;
    public InvalidTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
