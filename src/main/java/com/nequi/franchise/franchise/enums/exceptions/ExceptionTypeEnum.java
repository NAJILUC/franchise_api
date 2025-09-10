package com.nequi.franchise.franchise.enums.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionTypeEnum {

    INTERNAL_EXCEPTION("IE", "Internal Server"),
    REQUEST_EXCEPTION("RE", "Request"),
    VALIDATION_EXCEPTION("VE", "Validation"),
    NOT_FOUND_EXCEPTION("NF", "NotFound");

    private final String code;
    private final String value;

}
