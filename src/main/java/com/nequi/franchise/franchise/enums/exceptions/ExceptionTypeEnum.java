package com.nequi.franchise.franchise.enums.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionTypeEnum {

    INTERNAL_EXCEPTION("IE", "Internal Server Exception"),
    REQUEST_EXCEPTION("RE", "Request Exception"),
    VALIDATION_EXCEPTION("VE", "Validation Exception"),
    NOT_FOUND_EXCEPTION("NF", "Not Found Exception");

    private final String code;
    private final String value;

}
