package com.nequi.franchise.franchise.controllers.franchises;

import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.requests.franchises.FranchiseRequest;
import com.nequi.franchise.franchise.requests.franchises.UpdFranchiseRequest;
import com.nequi.franchise.franchise.requests.stores.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.franchises.TopProductStockResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.franchises.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de franquicias y sus sucursales.
 *
 * Base Path: /franchises
 */
@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    /**
     * ENDPOINTS GET
     */

    /**
     * Obtener productos más vendidos/stock en una franquicia.
     *
     * GET /franchises/{franchiseId}/top-products-stock
     *
     * @param franchiseId ID de la franquicia
     * @param page número de página (por defecto 0)
     * @param size tamaño de la página (por defecto 10)
     * @param column columna para ordenar (por defecto "storeId")
     * @param order orden (ASC o DESC)
     * @return Page con productos más relevantes por stock
     */
    @GetMapping(path = "/{franchiseId}/top-products-stock", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(@PathVariable Long franchiseId,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size,
                                                                             @RequestParam(defaultValue = "storeId") String column,
                                                                             @RequestParam(defaultValue = "ASC") String order) {
        return franchiseService.findTopProductStock(franchiseId, new PaginationObj(page, size, column, order));
    }

    /**
     * ENDPOINTS POST
     */

    /**
     * Crear una nueva franquicia.
     *
     * POST /franchises
     *
     * @param franchiseRequest objeto con la información de la franquicia
     * @return id y nombre de la nueva franquicia creada
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasicIdNameResponse> createFranchise(@Valid @RequestBody FranchiseRequest franchiseRequest){
        return franchiseService.createFranchise(franchiseRequest);
    }

    /**
     * Crear una sucursal dentro de una franquicia existente.
     *
     * POST /franchises/{franchiseId}/store
     *
     * @param franchiseId id de la franquicia a la que pertenece la sucursal
     * @param storeRequest datos de la sucursal a crear
     * @return información de la sucursal creada
     */
    @PostMapping(path = "/{franchiseId}/store", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StoreResponse> createStore(@PathVariable Long franchiseId, @Valid @RequestBody StoreRequest storeRequest) {
        return franchiseService.createStore(franchiseId, storeRequest);
    }

    /**
     * ENDPOINTS PUT
     */

    /**
     * Actualizar datos de una franquicia.
     *
     * PUT /franchises/{franchiseId}
     *
     * @param franchiseId id de la franquicia a actualizar
     * @param updFranchiseRequest objeto con los datos actualizados
     * @return id y nombre de la franquicia actualizada
     */
    @PutMapping(path = "/{franchiseId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasicIdNameResponse> updateFranchise(@PathVariable Long franchiseId, @Valid @RequestBody UpdFranchiseRequest updFranchiseRequest) {
        return franchiseService.updateFranchise(franchiseId, updFranchiseRequest);
    }
}
