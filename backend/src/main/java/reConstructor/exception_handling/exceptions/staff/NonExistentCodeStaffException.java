package reConstructor.exception_handling.exceptions.staff;

import org.springframework.http.HttpStatus;

public class NonExistentCodeStaffException extends RuntimeException {
    private HttpStatus status;

    public NonExistentCodeStaffException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
