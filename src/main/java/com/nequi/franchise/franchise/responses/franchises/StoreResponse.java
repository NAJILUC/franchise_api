package com.nequi.franchise.franchise.responses.franchises;


import com.nequi.franchise.franchise.entities.stores.Store;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    private BasicIdNameResponse franchise;

    public StoreResponse(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.franchise = new BasicIdNameResponse(store.getFranchise());
    }

}
