package reConstructor.exception_handling.exceptions.common_classes;

import org.springframework.http.HttpStatus;
// отправка статуса NO_CONTENT вместе с пустым телом письма после удаления сущности

public class EntityDeletedFromDbException extends RuntimeException {
    private HttpStatus status;

    public EntityDeletedFromDbException() {
    }

    public EntityDeletedFromDbException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
