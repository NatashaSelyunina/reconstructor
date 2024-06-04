package reConstructor.exception_handling.exceptions.moderator;
// невозможно удалить модератора, так как к данному модератору есть привязанные рестораны

import org.springframework.http.HttpStatus;

public class ModeratorCannotBeRemovedException extends RuntimeException {
    private HttpStatus status;

    public ModeratorCannotBeRemovedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
