package reConstructor.exception_handling.exceptions.access.jwt;
// invalid token signature

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;

public class InvalidTokenSignatureException extends SignatureException {
    private HttpStatus status;
    public InvalidTokenSignatureException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
