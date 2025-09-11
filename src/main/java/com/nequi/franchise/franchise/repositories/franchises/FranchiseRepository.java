package com.nequi.franchise.franchise.repositories.franchises;

import com.nequi.franchise.franchise.entities.franchises.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    boolean existsByNameAndIdNot(String name, Long franchiseId);
}
