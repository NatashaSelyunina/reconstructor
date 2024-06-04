package reConstructor.exception_handling.exceptions.image;
// неподдерживаемый формат фотографии

import org.springframework.http.HttpStatus;

public class UnsupportedImageFormatException extends RuntimeException {
    private HttpStatus status;

    public UnsupportedImageFormatException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
