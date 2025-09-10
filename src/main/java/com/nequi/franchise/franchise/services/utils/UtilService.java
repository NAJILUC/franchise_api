package com.nequi.franchise.franchise.services.utils;

import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.NotFoundException;

import java.util.Optional;

public class UtilService {

    public static <T> T checkOptionalEmpty(Optional<T> optional, ExceptionEnum exceptionEnum) {
        if (optional.isEmpty()) {
            throw new NotFoundException(exceptionEnum);
        }
        return optional.get();
    }
}
