package com.nequi.franchise.franchise.services.products;

import com.nequi.franchise.franchise.entities.products.Product;
import com.nequi.franchise.franchise.entities.stores.Store;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.exceptions.NotFoundException;
import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.repositories.products.ProductRepository;
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

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los productos.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    /**
     * Repository
     */
    private final ProductRepository productRepository;

    /**
     * Busca un producto por su ID.
     *
     * @param productId identificador único del producto
     * @return la entidad {@link Product} encontrada
     * @throws NotFoundException si el producto no existe
     */
    private Product findByProductId(Long productId) {
        return UtilService.checkOptionalEmpty(productRepository.findById(productId), ExceptionEnum.STOR02);
    }

    /**
     * Crea un nuevo producto dentro de una sucursal.
     *
     * @param store sucursal a la que pertenece el producto
     * @param productRequest datos de entrada para crear el producto
     * @return representación del producto creado
     * @throws BadRequestException si ya existe un producto con el mismo nombre en la sucursal
     */
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

    /**
     * Valida que no exista otro producto con el mismo nombre en la sucursal.
     *
     * @param store sucursal a validar
     * @param name nombre del producto
     * @param productId id del producto a excluir de la validación (para updates)
     * @throws BadRequestException si ya existe un producto duplicado
     */
    private void validUniqueNameByStore(Store store, String name, Long productId) {
        if (productRepository.existsByStoreAndNameAndIdNot(store, name, productId)) {
            throw new BadRequestException(ExceptionEnum.PROD01);
        }
    }

    /**
     * Elimina un producto de una sucursal.
     *
     * @param store sucursal propietaria del producto
     * @param productId identificador del producto a eliminar
     * @throws NotFoundException si el producto no existe en la sucursal
     */
    @Transactional
    public void deleteProduct(Store store, Long productId) {
        Product product = productRepository.findByStoreAndId(store, productId)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.PROD02));

        productRepository.delete(product);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param productId identificador del producto a actualizar
     * @param updProductRequest datos actualizados del producto
     * @return producto actualizado dentro de un {@link ResponseEntity}
     * @throws BadRequestException si el nuevo nombre ya existe en la sucursal
     * @throws NotFoundException si el producto no existe
     */
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

    /**
     * Obtiene el listado de productos más relevantes por stock dentro de una franquicia.
     *
     * @param franchiseId identificador de la franquicia
     * @param paginationObj parámetros de paginación y ordenamiento
     * @return página de productos ordenados por stock
     */
    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(Long franchiseId, PaginationObj paginationObj) {
        Pageable pageable = UtilService.buildPageable(paginationObj);
        Page<TopProductStockResponse> response = productRepository.topProductStock(franchiseId, pageable)
                .map(TopProductStockResponse::new);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
