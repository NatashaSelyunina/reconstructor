package reConstructor.exception_handling.exceptions.access.jwt;
// invalid format token

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;

public class InvalidFormatTokenException extends MalformedJwtException {
    private HttpStatus status;
    public InvalidFormatTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
