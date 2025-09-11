package com.nequi.franchise.franchise.controllers.products;

import com.nequi.franchise.franchise.requests.products.UpdProductRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.services.products.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de productos.
 *
 * Base Path: /products
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * ENDPOINTS GET
     */

    /**
     * Actualizar la información de un producto existente.
     *
     * PUT /products/{productId}
     *
     * @param productId ID del producto a actualizar
     * @param updProductRequest objeto con los nuevos datos del producto
     * @return información del producto actualizado
     */
    @PutMapping(path = "/{productId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody UpdProductRequest updProductRequest) {
        return productService.updateProduct(productId, updProductRequest);
    }
}
