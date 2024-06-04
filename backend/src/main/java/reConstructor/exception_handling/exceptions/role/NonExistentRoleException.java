package reConstructor.exception_handling.exceptions.role;

import org.springframework.http.HttpStatus;

public class NonExistentRoleException extends RuntimeException {

  private HttpStatus status;

  public NonExistentRoleException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

}
