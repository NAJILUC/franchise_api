package com.nequi.franchise.franchise.responses.franchises;


import com.nequi.franchise.franchise.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private StoreResponse store;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.store = new StoreResponse(product.getStore());
    }

}
