package com.nequi.franchise.franchise.requests.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdProductRequest {

    @NotNull
    @Min(0)
    private Long stock;
}
