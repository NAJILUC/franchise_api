package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.entities.Product;
import com.nequi.franchise.franchise.entities.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.exceptions.NotFoundException;
import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.repositories.ProductRepository;
import com.nequi.franchise.franchise.requests.products.ProductRequest;
import com.nequi.franchise.franchise.requests.products.UpdProductRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.responses.franchises.TopProductStockResponse;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private Product findByProductId(Long productId) {
        return UtilService.checkOptionalEmpty(productRepository.findById(productId), ExceptionEnum.STOR02);
    }

    @Transactional
    public ProductResponse createProduct(Store store, ProductRequest productRequest){
        Product product = new Product();
        this.validUniqueNameByStore(store, productRequest.getName(), null);
        product.setStore(store);
        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        productRepository.save(product);
        return new ProductResponse(product);
    }

    private void validUniqueNameByStore(Store store, String name, Long productId) {
        if (productRepository.existsByStoreAndNameAndIdNot(store, name, productId)) {
            throw new BadRequestException(ExceptionEnum.PROD01);
        }
    }

    @Transactional
    public void deleteProduct(Store store, Long productId) {
        Product product = productRepository.findByStoreAndId(store, productId)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.PROD02));

        productRepository.delete(product);
    }

    @Transactional
    public ResponseEntity<ProductResponse> updateProduct(Long productId, UpdProductRequest updProductRequest) {
        Product product = this.findByProductId(productId);
        if (updProductRequest.getStock() != null && !Objects.equals(updProductRequest.getStock(), product.getStock())) {
            product.setStock(updProductRequest.getStock());
        }
        if (updProductRequest.getName() != null && !updProductRequest.getName().isEmpty()
                && !updProductRequest.getName().equals(product.getName())) {
            this.validUniqueNameByStore(product.getStore(), updProductRequest.getName(), product.getId());
            product.setName(updProductRequest.getName());
        }
        productRepository.save(product);
        ProductResponse response = new ProductResponse(product);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(Long franchiseId, PaginationObj paginationObj) {
        Pageable pageable = UtilService.buildPageable(paginationObj);
        Page<TopProductStockResponse> response = productRepository.topProductStock(franchiseId, pageable)
                .map(TopProductStockResponse::new);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
