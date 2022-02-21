package io.github.arturtcs.apiclients.resources.exceptions;

import io.github.arturtcs.apiclients.services.exceptions.AttributeNullOrEntityInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class AttributeNullOrEntityInvalidExceptionHandler {

    @ExceptionHandler(AttributeNullOrEntityInvalidException.class)
    public ResponseEntity<StandardError> attributeNullOrEntityInvalidException(AttributeNullOrEntityInvalidException ex, HttpServletRequest request) {
        StandardError err = new StandardError();
                err.setTimestamp(Instant.now());
                err.setStatus(HttpStatus.BAD_REQUEST.value());
                err.setError("Attributes can't be empty");
                err.setMessage(ex.getMessage());
                err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }
}
