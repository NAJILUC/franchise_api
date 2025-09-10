package com.nequi.franchise.franchise.responses.exceptions;


import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicErrorResponse {

    private BasicErrorDetailResponse errors;

    public BasicErrorResponse(ExceptionEnum exceptionEnum) {
        this.errors = new BasicErrorDetailResponse(
                exceptionEnum.getCode(),
                Collections.singletonList(exceptionEnum.getMessage())
        );
    }

}
