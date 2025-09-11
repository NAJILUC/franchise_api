package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Franchise;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.StoreRepository;
import com.nequi.franchise.franchise.requests.products.ProductRequest;
import com.nequi.franchise.franchise.requests.stores.StoreRequest;
import com.nequi.franchise.franchise.requests.stores.UpdStoreRequest;
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
        this.validUniqueName(franchise, storeRequest.getName(), null);
        store.setFranchise(franchise);
        store.setName(storeRequest.getName());
        storeRepository.save(store);
        return new StoreResponse(store);
    }

    private void validUniqueName(Franchise franchise, String name, Long storeId) {
        if (storeRepository.existsByFranchiseAndNameAndIdNot(franchise, name, storeId)) {
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

    @Transactional
    public ResponseEntity<StoreResponse> updateStore(Long storeId, UpdStoreRequest updStoreRequest) {
        Store store = this.findByStoreId(storeId);
        this.validUniqueName(store.getFranchise(), updStoreRequest.getName(), store.getId());
        if(!store.getName().equals(updStoreRequest.getName())){
            store.setName(updStoreRequest.getName());
        }
        storeRepository.save(store);
        StoreResponse response = new StoreResponse(store);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
