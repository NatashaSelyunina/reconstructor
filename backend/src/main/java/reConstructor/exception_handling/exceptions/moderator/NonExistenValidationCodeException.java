package reConstructor.exception_handling.exceptions.moderator;

import org.springframework.http.HttpStatus;

public class NonExistenValidationCodeException extends RuntimeException {

  private HttpStatus status;

  public NonExistenValidationCodeException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
