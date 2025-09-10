package com.nequi.franchise.franchise.repositories;

import com.nequi.franchise.franchise.entities.Product;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.projections.TopProductStockProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByStoreAndName(Store store, String name);

    Optional<Product> findByStoreAndId(Store store, Long id);

    @Query(value = """
            SELECT s.id AS storeId,
                   s.name AS storeName,
                   p.id AS productId,
                   p.name AS productName,
                   p.stock AS productStock
            FROM stores s
            JOIN products p ON p.store_id = s.id
            WHERE p.stock =
                (SELECT MAX(p2.stock)
                 FROM products p2
                 WHERE p2.store_id = s.id)
            AND s.franchise_id = :franchiseId
            """, nativeQuery = true)
    Page<TopProductStockProjection> topProductStock(Long franchiseId, Pageable pageable);
}
