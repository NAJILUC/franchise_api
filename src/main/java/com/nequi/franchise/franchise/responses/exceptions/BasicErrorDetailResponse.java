package com.nequi.franchise.franchise.responses.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicErrorDetailResponse {

    private String code;
    private String description;
    private List<String> errors;

    public BasicErrorDetailResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }


}
