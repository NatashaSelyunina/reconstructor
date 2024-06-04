package reConstructor.exception_handling.exceptions.staff;

import org.springframework.http.HttpStatus;
// по введенной фамилии сотрудник не найден

public class NonExistentSurnameStaffException extends RuntimeException {
    private HttpStatus status;

    public NonExistentSurnameStaffException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
