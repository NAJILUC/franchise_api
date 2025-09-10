package com.nequi.franchise.franchise.enums.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    FRANCHISE01("Franchise name must be unique", ExceptionTypeEnum.REQUEST_EXCEPTION.getValue());

    private final String code;
    private final String message;
    private final String description;

    ExceptionEnum(String message, String description) {
        this.code = this.name();
        this.message = message;
        this.description = description;
    }
}
