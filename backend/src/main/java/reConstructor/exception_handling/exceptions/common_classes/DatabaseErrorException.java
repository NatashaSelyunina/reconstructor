package reConstructor.exception_handling.exceptions.common_classes;
// класс для исключений при ошибке ответа от базы данных

import org.springframework.http.HttpStatus;

public class DatabaseErrorException extends RuntimeException {
    private HttpStatus status;

    public DatabaseErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
