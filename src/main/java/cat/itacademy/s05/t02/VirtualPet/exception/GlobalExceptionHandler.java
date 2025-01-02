package cat.itacademy.s05.t02.VirtualPet.exception;

import cat.itacademy.s05.t02.VirtualPet.exception.custom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PetNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePetNameAlreadyExistsException(PetNameAlreadyExistsException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PetNameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePetNameNotFoundException(PetNameNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetIsInactiveException.class)
    public ResponseEntity<ErrorResponse> handlePetIsInactiveException(PetIsInactiveException e) {
        return buildErrorResponse(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UsernameNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<ErrorResponse> handleAuthenticationExceptions(Exception e) {
        // (UsernameNotFoundException can only occur during authentication)
        log.error("Authentication error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Wrong Credentials", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus status) {
        log.error("Error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), status);
        return new ResponseEntity<>(errorResponse, status);
    }
}
