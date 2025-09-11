package com.nequi.franchise.franchise.controllers.stores;

import com.nequi.franchise.franchise.requests.products.ProductRequest;
import com.nequi.franchise.franchise.requests.stores.UpdStoreRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.services.stores.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de sucursales y sus productos.
 *
 * Base Path: /stores
 */
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * ENDPOINTS POST
     */

    /**
     * Agregar un producto a una sucursal.
     *
     * POST /stores/{storeId}/product
     *
     * @param storeId ID de la sucursal donde se agregará el producto
     * @param productRequest datos del producto a registrar
     * @return información del producto creado
     */
    @PostMapping(path = "/{storeId}/product", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> createStore(@PathVariable Long storeId, @Valid @RequestBody ProductRequest productRequest) {
        return storeService.createProduct(storeId, productRequest);
    }

    /**
     * ENDPOINTS PUT
     */

    /**
     *  ️Actualizar la información de una sucursal.
     *
     * PUT /stores/{storeId}
     *
     * @param storeId ID de la sucursal a actualizar
     * @param updStoreRequest objeto con los nuevos datos de la sucursal
     * @return información de la sucursal actualizada
     */
    @PutMapping(path = "/{storeId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StoreResponse> updateStore(@PathVariable Long storeId, @Valid @RequestBody UpdStoreRequest updStoreRequest) {
        return storeService.updateStore(storeId, updStoreRequest);
    }

    /**
     * ENDPOINTS DELETE
     */

    /**
     * Eliminar un producto de una sucursal.
     *
     * DELETE /stores/{storeId}/product/{productId}
     *
     * @param storeId ID de la sucursal
     * @param productId ID del producto a eliminar
     * @return vacío (HTTP 204 No Content si se elimina correctamente)
     */
    @DeleteMapping(path = "/{storeId}/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return storeService.deleteProduct(storeId, productId);
    }
}
