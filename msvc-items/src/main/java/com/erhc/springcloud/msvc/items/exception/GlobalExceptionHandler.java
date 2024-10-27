package com.erhc.springcloud.msvc.items.exception;

import com.erhc.springcloud.msvc.items.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getReason(), // El mensaje personalizado que configuraste
                ex.getStatusCode().value(),
                ex.getStatusCode().toString() // Cambiado a `name()` para obtener el nombre del status
        );
        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR" // Texto fijo para el tipo de error.
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
