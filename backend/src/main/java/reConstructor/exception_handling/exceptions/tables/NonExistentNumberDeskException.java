package reConstructor.exception_handling.exceptions.tables;
// по введенному номеру стол не найден

import org.springframework.http.HttpStatus;

public class NonExistentNumberDeskException extends RuntimeException {
    private HttpStatus status;

    public NonExistentNumberDeskException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
