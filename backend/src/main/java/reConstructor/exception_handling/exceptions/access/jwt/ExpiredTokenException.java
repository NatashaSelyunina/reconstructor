package reConstructor.exception_handling.exceptions.access.jwt;
// expired token

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends ExpiredJwtException {
    private HttpStatus status;

    public ExpiredTokenException(String message, HttpStatus status) {
        super(null, null, message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
