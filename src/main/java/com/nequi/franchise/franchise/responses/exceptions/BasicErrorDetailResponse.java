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
    private List<String> fields;


}
