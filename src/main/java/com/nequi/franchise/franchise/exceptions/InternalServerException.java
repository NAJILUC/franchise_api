package com.nequi.franchise.franchise.exceptions;

import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.responses.exceptions.BasicErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InternalServerException extends RuntimeException {

    private final transient BasicErrorResponse errorResponse;

    public InternalServerException(ExceptionEnum exceptionCodeEnum) {
        this.errorResponse = new BasicErrorResponse(exceptionCodeEnum);
    }

}