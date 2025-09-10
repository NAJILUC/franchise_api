package com.nequi.franchise.franchise.repositories;

import com.nequi.franchise.franchise.entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
