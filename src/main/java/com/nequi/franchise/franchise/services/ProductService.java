package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Product;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.ProductRepository;
import com.nequi.franchise.franchise.requests.ProductRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(Store store, ProductRequest productRequest){
        Product product = new Product();
        this.validUniqueNameByStore(store, productRequest.getName());
        product.setStore(store);
        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        productRepository.save(product);
        return new ProductResponse(product);
    }

    private void validUniqueNameByStore(Store store, String name) {
        if (productRepository.existsByStoreAndName(store, name)) {
            throw new BadRequestException(ExceptionEnum.PROD01);
        }
    }
}
