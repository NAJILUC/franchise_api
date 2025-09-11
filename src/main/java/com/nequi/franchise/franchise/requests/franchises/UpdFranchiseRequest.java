package com.nequi.franchise.franchise.requests.franchises;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdFranchiseRequest {

    @NotBlank
    private String name;
}
