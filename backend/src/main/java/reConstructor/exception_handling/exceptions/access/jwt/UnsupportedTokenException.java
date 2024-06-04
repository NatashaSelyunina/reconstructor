package reConstructor.exception_handling.exceptions.access.jwt;
// unsupported token

import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;

public class UnsupportedTokenException extends UnsupportedJwtException {
    private HttpStatus status;
    public UnsupportedTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
