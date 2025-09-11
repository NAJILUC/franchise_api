package com.nequi.franchise.franchise.repositories.stores;

import com.nequi.franchise.franchise.entities.franchises.Franchise;
import com.nequi.franchise.franchise.entities.stores.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    boolean existsByFranchiseAndNameAndIdNot(Franchise franchise, String name, Long storeId);
}
