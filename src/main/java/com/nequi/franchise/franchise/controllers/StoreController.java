package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.requests.ProductRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.services.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping(path = "/{storeId}/product", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> createStore(@PathVariable Long storeId, @Valid @RequestBody ProductRequest productRequest) {
        return storeService.createProduct(storeId, productRequest);
    }

    @DeleteMapping(path = "/{storeId}/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return storeService.deleteProduct(storeId, productId);
    }
}
