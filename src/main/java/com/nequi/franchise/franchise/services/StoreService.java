package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.StoreRepository;
import com.nequi.franchise.franchise.requests.ProductRequest;
import com.nequi.franchise.franchise.requests.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private final ProductService productService;

    private Store findByStoreId(Long storeId){
        return UtilService.checkOptionalEmpty(storeRepository.findById(storeId), ExceptionEnum.STOR02);
    }

    @Transactional
    public StoreResponse createStore(Franchise franchise, StoreRequest storeRequest) {
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

    public ResponseEntity<ProductResponse> createProduct(Long storeId, ProductRequest productRequest) {
        Store store = this.findByStoreId(storeId);
        ProductResponse response =productService.createProduct(store, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Void> deleteProduct(Long storeId, Long productId) {
        Store store = this.findByStoreId(storeId);
        productService.deleteProduct(store, productId);
        return ResponseEntity.noContent().build();
    }
}
