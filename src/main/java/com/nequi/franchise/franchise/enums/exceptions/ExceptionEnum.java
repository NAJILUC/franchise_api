package com.nequi.franchise.franchise.enums.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    USR10("User manager is not found", ExceptionTypeEnum.REQUEST_EXCEPTION.getValue());


    private final String code;
    private final String message;
}
