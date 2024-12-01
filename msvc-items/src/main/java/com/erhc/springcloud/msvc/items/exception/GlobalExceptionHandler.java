package com.erhc.springcloud.msvc.items.exception;

import com.erhc.springcloud.msvc.items.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        logger.error("ResponseStatusException: {}", ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getReason(), // El mensaje personalizado que configuraste
                ex.getStatusCode().value(),
                ex.getStatusCode().toString() // Cambiado a `name()` para obtener el nombre del status
        );
        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        logger.error("GeneralException: {}", ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR" // Texto fijo para el tipo de error.

        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException ex) {
        logger.error("ProductNotFoundException: {}", ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
