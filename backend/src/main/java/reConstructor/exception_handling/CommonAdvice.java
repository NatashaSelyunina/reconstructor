package reConstructor.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reConstructor.exception_handling.exceptions.access.jwt.*;
import reConstructor.exception_handling.exceptions.common_classes.*;
import reConstructor.exception_handling.exceptions.access.UnauthorizedAccessException;
import reConstructor.exception_handling.exceptions.role.NonExistentRoleException;
import reConstructor.exception_handling.exceptions.staff.NotFoundWorkingStaffException;
import reConstructor.exception_handling.exceptions.tables.NonExistentNumberDeskException;
import reConstructor.exception_handling.exceptions.image.FileSizeIsTooLargeException;
import reConstructor.exception_handling.exceptions.image.UnsupportedImageFormatException;
import reConstructor.exception_handling.exceptions.moderator.*;
import reConstructor.exception_handling.exceptions.access.AuthenticationException;
import reConstructor.exception_handling.exceptions.restaurant.NonExistentCodeRestaurantException;
import reConstructor.exception_handling.exceptions.staff.NonExistentCodeStaffException;
import reConstructor.exception_handling.exceptions.staff.NonExistentSurnameStaffException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonAdvice {

    // FORBIDDEN
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Response> handleException(UnauthorizedAccessException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());

    }

    // UNAUTHORIZED
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response> handleException(AuthenticationException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // UNAUTHORIZED
    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Response> handleException(ExpiredTokenException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(InvalidFormatTokenException.class)
    public ResponseEntity<Response> handleException(InvalidFormatTokenException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // UNAUTHORIZED
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Response> handleException(InvalidTokenException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // UNAUTHORIZED
    @ExceptionHandler(InvalidTokenSignatureException.class)
    public ResponseEntity<Response> handleException(InvalidTokenSignatureException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // UNSUPPORTED MEDIA TYPE
    @ExceptionHandler(UnsupportedTokenException.class)
    public ResponseEntity<Response> handleException(UnsupportedTokenException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    // TODO добавить при валидации полей информативные сообщения для пользователя
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Response response = Response.builder()
                .message("Validation error")
                .errors(errors)
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    // PAYLOAD_TOO_LARGE
    @ExceptionHandler(FileSizeIsTooLargeException.class)
    public ResponseEntity<Response> handleException(FileSizeIsTooLargeException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // CONFLICT
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Response> handleException(DuplicateEmailException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // INTERNAL_SERVER_ERROR
    @ExceptionHandler(DatabaseErrorException.class)
    public ResponseEntity<Response> handleException(DatabaseErrorException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentIdException.class)
    public ResponseEntity<Response> handleException(NonExistentIdException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // CONFLICT
    @ExceptionHandler(ModeratorCannotBeRemovedException.class)
    public ResponseEntity<Response> handleException(ModeratorCannotBeRemovedException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // N0_CONTENT
    @ExceptionHandler(NotFoundActiveEntitiesException.class)
    public ResponseEntity<Response> handleException(NotFoundActiveEntitiesException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentCodeRestaurantException.class)
    public ResponseEntity<Response> handleException(NonExistentCodeRestaurantException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // UNSUPPORTED_MEDIA_TYPE
    @ExceptionHandler(UnsupportedImageFormatException.class)
    public ResponseEntity<Response> handleException(UnsupportedImageFormatException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentNameException.class)
    public ResponseEntity<Response> handleException(NonExistentNameException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentNumberDeskException.class)
    public ResponseEntity<Response> handleException(NonExistentNumberDeskException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentCodeStaffException.class)
    public ResponseEntity<Response> handleException(NonExistentCodeStaffException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistentSurnameStaffException.class)
    public ResponseEntity<Response> handleException(NonExistentSurnameStaffException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(EntityDeletedFromDbException.class)
    public ResponseEntity<Response> handleException() {
        Response response = new Response();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    // BAD_REQUEST
    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<Response> handleException(EmptyRequestBodyException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    // BAD_REQUEST
    @ExceptionHandler(NonExistenModeratorWithThisEmail.class)
    public ResponseEntity<Response> handleException(NonExistenModeratorWithThisEmail e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NonExistentRoleException.class)
    public ResponseEntity<Response> handleException(NonExistentRoleException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NotFoundWorkingStaffException.class)
    public ResponseEntity<Response> handleException(NotFoundWorkingStaffException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Response.builder()
                        .message(e.getMessage())
                        .build());
    }
}
