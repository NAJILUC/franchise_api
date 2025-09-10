package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.requests.ProductRequest;
import com.nequi.franchise.franchise.requests.UpdProductRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping(path = "/{productId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody UpdProductRequest updProductRequest) {
        return productService.updateProduct(productId, updProductRequest);
    }
}
