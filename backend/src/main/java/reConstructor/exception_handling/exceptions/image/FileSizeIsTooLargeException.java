package reConstructor.exception_handling.exceptions.image;

import org.springframework.http.HttpStatus;
// размер загружаемых файлов превышает допустимый

public class FileSizeIsTooLargeException extends RuntimeException {
    private HttpStatus status;

    public FileSizeIsTooLargeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
