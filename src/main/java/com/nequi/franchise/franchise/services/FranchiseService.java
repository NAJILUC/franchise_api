package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.repositories.FranchiseRepository;
import com.nequi.franchise.franchise.requests.FranchiseRequest;
import com.nequi.franchise.franchise.requests.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.franchises.TopProductStockResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    private final StoreService storeService;
    private final ProductService productService;

    private Franchise findByFranchiseId(Long franchiseId){
        return UtilService.checkOptionalEmpty(franchiseRepository.findById(franchiseId), ExceptionEnum.FRAN02);
    }

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
        Franchise franchise = this.findByFranchiseId(franchiseId);
        StoreResponse response = storeService.createStore(franchise, storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(Long franchiseId, PaginationObj paginationObj) {
        this.findByFranchiseId(franchiseId);
        return productService.findTopProductStock(franchiseId, paginationObj);
    }
}
