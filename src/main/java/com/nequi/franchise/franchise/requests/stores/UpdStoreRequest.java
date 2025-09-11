package com.nequi.franchise.franchise.requests.stores;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdStoreRequest {

    @NotBlank
    private String name;
}
