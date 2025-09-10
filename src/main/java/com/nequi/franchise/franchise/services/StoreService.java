package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.StoreRepository;
import com.nequi.franchise.franchise.requests.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public StoreResponse createFranchise(Franchise franchise, StoreRequest storeRequest){
        Store store = new Store();
        this.validUniqueName(storeRequest.getName());
        store.setFranchise(franchise);
        store.setName(storeRequest.getName());
        storeRepository.save(store);
        return new StoreResponse(store);
    }

    private void validUniqueName(String name) {
        if (storeRepository.existsByName(name)) {
            throw new BadRequestException(ExceptionEnum.STOR01);
        }
    }
}
