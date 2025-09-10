package com.nequi.franchise.franchise.projections;

public interface TopProductStockProjection {
    Long getStoreId();
    String getStoreName();
    Long getProductId();
    String getProductName();
    Long getProductStock();
}
