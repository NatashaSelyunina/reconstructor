package reConstructor.exception_handling.exceptions.access;
// неверный email или пароль

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {
    private HttpStatus status;

    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
