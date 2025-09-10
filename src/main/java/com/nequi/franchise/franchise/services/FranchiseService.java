package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.FranchiseRepository;
import com.nequi.franchise.franchise.requests.FranchiseRequest;
import com.nequi.franchise.franchise.requests.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    private final StoreService storeService;

    @Transactional
    public ResponseEntity<BasicIdNameResponse> createFranchise(FranchiseRequest franchiseRequest) {
        Franchise franchise = new Franchise();
        this.validUniqueName(franchiseRequest.getName());
        franchise.setName(franchiseRequest.getName());
        franchiseRepository.save(franchise);
        BasicIdNameResponse response = new BasicIdNameResponse(franchise);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private void validUniqueName(String name) {
        if (franchiseRepository.existsByName(name)) {
            throw new BadRequestException(ExceptionEnum.FRAN01);
        }
    }

    public ResponseEntity<StoreResponse> createStore(Long franchiseId, StoreRequest storeRequest) {
        Franchise franchise = UtilService.checkOptionalEmpty(franchiseRepository.findById(franchiseId), ExceptionEnum.FRAN02);
        StoreResponse response = storeService.createStore(franchise, storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
