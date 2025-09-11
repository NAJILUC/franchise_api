package com.nequi.franchise.franchise.repositories;

import com.nequi.franchise.franchise.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    boolean existsByNameAndIdNot(String name, Long storeId);
}
