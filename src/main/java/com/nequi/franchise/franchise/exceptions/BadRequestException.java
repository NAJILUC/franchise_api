package com.nequi.franchise.franchise.exceptions;

import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.responses.exceptions.BasicErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

    private final transient BasicErrorResponse errorResponse;

    public BadRequestException(ExceptionEnum exceptionCodeEnum) {
        this.errorResponse = new BasicErrorResponse(exceptionCodeEnum);
    }

}