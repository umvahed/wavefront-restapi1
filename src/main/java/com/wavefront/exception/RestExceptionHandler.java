package com.wavefront.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is the class that is used to handle exception and build appropriate
 * error response
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handles shapes related exception
     * @param ex
     * @return
     */
    @ExceptionHandler(ShapeException.class)
    protected ResponseEntity<Object> handleShapeException(ShapeException ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setError("error while processing request");
        return buildResponseEntity(apiError);
    }

    /**
     * build error response along with Bad request
     * @param apiError
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

  }

