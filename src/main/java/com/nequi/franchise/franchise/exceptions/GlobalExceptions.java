package com.nequi.franchise.franchise.exceptions;

import com.nequi.franchise.franchise.enums.exceptions.ExceptionTypeEnum;
import com.nequi.franchise.franchise.responses.exceptions.BasicErrorDetailResponse;
import com.nequi.franchise.franchise.responses.exceptions.BasicErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BasicErrorResponse> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorResponse());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BasicErrorResponse> handleBadRequestException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getErrorResponse());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BasicErrorResponse> handleBadGatewayException(InternalServerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getErrorResponse());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BasicErrorResponse> handleException(IllegalArgumentException e) {
        BasicErrorResponse errorResponse = new BasicErrorResponse();
        errorResponse.setErrors(new BasicErrorDetailResponse(ExceptionTypeEnum.VALIDATION_EXCEPTION.getCode(),
                ExceptionTypeEnum.VALIDATION_EXCEPTION.getValue(),
                Collections.singletonList(e.getMessage())));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BasicErrorResponse> handleException(MethodArgumentNotValidException e) {
        BasicErrorResponse errorResponse = new BasicErrorResponse();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResponse.setErrors(new BasicErrorDetailResponse(ExceptionTypeEnum.VALIDATION_EXCEPTION.getCode(),
                    ExceptionTypeEnum.VALIDATION_EXCEPTION.getValue(),
                    Collections.singletonList(fieldError.getDefaultMessage())));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
