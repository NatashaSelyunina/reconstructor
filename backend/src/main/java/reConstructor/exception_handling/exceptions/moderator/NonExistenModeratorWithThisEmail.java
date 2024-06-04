package reConstructor.exception_handling.exceptions.moderator;

import org.springframework.http.HttpStatus;

public class NonExistenModeratorWithThisEmail extends RuntimeException {

    private HttpStatus status;

    public NonExistenModeratorWithThisEmail(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
