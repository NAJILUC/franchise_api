package com.nequi.franchise.franchise.responses.franchises;


import com.nequi.franchise.franchise.projections.TopProductStockProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductStockResponse {

    private Long storeId;
    private String storeName;
    private Long productId;
    private String productName;
    private Long productStock;

    public TopProductStockResponse(TopProductStockProjection topProductStockProjection) {
        this.storeId = topProductStockProjection.getStoreId();
        this.storeName = topProductStockProjection.getStoreName();
        this.productId = topProductStockProjection.getProductId();
        this.productName = topProductStockProjection.getProductName();
        this.productStock = topProductStockProjection.getProductStock();
    }

}
