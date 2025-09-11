package com.nequi.franchise.franchise.services.stores;

import com.nequi.franchise.franchise.entities.franchises.Franchise;
import com.nequi.franchise.franchise.entities.stores.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.repositories.stores.StoreRepository;
import com.nequi.franchise.franchise.requests.products.ProductRequest;
import com.nequi.franchise.franchise.requests.stores.StoreRequest;
import com.nequi.franchise.franchise.requests.stores.UpdStoreRequest;
import com.nequi.franchise.franchise.responses.franchises.ProductResponse;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.services.products.ProductService;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las sucursales.
 */
@Service
@RequiredArgsConstructor
public class StoreService {

    /**
     * Repository
     */
    private final StoreRepository storeRepository;

    /**
     * Services
     */
    private final ProductService productService;

    /**
     * Busca una sucursal por su ID.
     *
     * @param storeId identificador único de la sucursal
     * @return la entidad {@link Store} encontrada
     * @throws com.nequi.franchise.franchise.exceptions.NotFoundException si la sucursal no existe
     */
    private Store findByStoreId(Long storeId){
        return UtilService.checkOptionalEmpty(storeRepository.findById(storeId), ExceptionEnum.STOR02);
    }

    /**
     * Crea una nueva sucursal dentro de una franquicia.
     *
     * @param franchise franquicia a la que pertenece la sucursal
     * @param storeRequest datos de entrada para crear la sucursal
     * @return representación de la sucursal creada
     * @throws BadRequestException si ya existe una sucursal con el mismo nombre en la franquicia
     */
    @Transactional
    public StoreResponse createStore(Franchise franchise, StoreRequest storeRequest) {
        Store store = new Store();
        this.validUniqueName(franchise, storeRequest.getName(), null);
        store.setFranchise(franchise);
        store.setName(storeRequest.getName());
        storeRepository.save(store);
        return new StoreResponse(store);
    }

    /**
     * Valida que no exista otra sucursal con el mismo nombre dentro de la franquicia.
     *
     * @param franchise franquicia a validar
     * @param name nombre de la sucursal
     * @param storeId id de la sucursal a excluir (para updates)
     * @throws BadRequestException si ya existe otra sucursal con el mismo nombre
     */
    private void validUniqueName(Franchise franchise, String name, Long storeId) {
        if (storeRepository.existsByFranchiseAndNameAndIdNot(franchise, name, storeId)) {
            throw new BadRequestException(ExceptionEnum.STOR01);
        }
    }

    /**
     * Crea un nuevo producto dentro de una sucursal.
     *
     * @param storeId identificador de la sucursal
     * @param productRequest datos de entrada para crear el producto
     * @return representación del producto creado en la sucursal
     */
    public ResponseEntity<ProductResponse> createProduct(Long storeId, ProductRequest productRequest) {
        Store store = this.findByStoreId(storeId);
        ProductResponse response =productService.createProduct(store, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Elimina un producto de una sucursal.
     *
     * @param storeId identificador de la sucursal
     * @param productId identificador del producto a eliminar
     * @return respuesta vacía con código HTTP 204
     */
    public ResponseEntity<Void> deleteProduct(Long storeId, Long productId) {
        Store store = this.findByStoreId(storeId);
        productService.deleteProduct(store, productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza los datos de una sucursal existente.
     *
     * @param storeId identificador de la sucursal a actualizar
     * @param updStoreRequest datos actualizados de la sucursal
     * @return sucursal actualizada dentro de un {@link ResponseEntity}
     * @throws BadRequestException si el nuevo nombre ya existe en la franquicia
     */
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
