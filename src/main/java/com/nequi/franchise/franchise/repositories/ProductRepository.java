package com.nequi.franchise.franchise.repositories;

import com.nequi.franchise.franchise.entities.Product;
import com.nequi.franchise.franchise.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByStoreAndName(Store store, String name);

    Optional<Product> findByStoreAndId(Store store, Long id);
}
