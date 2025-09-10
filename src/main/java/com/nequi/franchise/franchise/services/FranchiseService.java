package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.repositories.FranchiseRepository;
import com.nequi.franchise.franchise.requests.FranchiseRequest;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Transactional
    public BasicIdNameResponse createFranchise(FranchiseRequest franchiseRequest){
        Franchise franchise = new Franchise();
        franchise.setName(franchiseRequest.getName());
        franchiseRepository.save(franchise);
        return new BasicIdNameResponse(franchise);
    }
}
