package reConstructor.exception_handling.exceptions.restaurant;
// по введенному коду ресторан не найден

import org.springframework.http.HttpStatus;

public class NonExistentCodeRestaurantException extends RuntimeException {
    private HttpStatus status;

    public NonExistentCodeRestaurantException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
