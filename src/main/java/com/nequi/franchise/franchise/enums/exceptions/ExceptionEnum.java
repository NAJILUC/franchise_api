package com.nequi.franchise.franchise.enums.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    FRAN01("Franchise name must be unique", ExceptionTypeEnum.REQUEST_EXCEPTION.getValue()),
    FRAN02("Franchise by id was not found", ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getValue()),

    STOR01("Store name must be unique", ExceptionTypeEnum.REQUEST_EXCEPTION.getValue()),
    STOR02("Store by id was not found", ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getValue()),

    PROD01("Product name must be unique for store", ExceptionTypeEnum.REQUEST_EXCEPTION.getValue()),
    PROD02("Product by id and store was not found", ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getValue()),
    PROD03("Product by id was not found", ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getValue()),
    ;

    private final String code;
    private final String message;
    private final String description;

    ExceptionEnum(String message, String description) {
        this.code = this.name();
        this.message = message;
        this.description = description;
    }
}
