package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.requests.franchises.FranchiseRequest;
import com.nequi.franchise.franchise.requests.franchises.UpdFranchiseRequest;
import com.nequi.franchise.franchise.requests.stores.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.franchises.TopProductStockResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @GetMapping(path = "/{franchiseId}/top-products-stock", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(@PathVariable Long franchiseId,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size,
                                                                             @RequestParam(defaultValue = "storeId") String column,
                                                                             @RequestParam(defaultValue = "ASC") String order) {
        return franchiseService.findTopProductStock(franchiseId, new PaginationObj(page, size, column, order));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasicIdNameResponse> createFranchise(@Valid @RequestBody FranchiseRequest franchiseRequest){
        return franchiseService.createFranchise(franchiseRequest);
    }

    @PostMapping(path = "/{franchiseId}/store", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StoreResponse> createStore(@PathVariable Long franchiseId, @Valid @RequestBody StoreRequest storeRequest) {
        return franchiseService.createStore(franchiseId, storeRequest);
    }

    @PutMapping(path = "/{franchiseId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasicIdNameResponse> updateFranchise(@PathVariable Long franchiseId, @Valid @RequestBody UpdFranchiseRequest updFranchiseRequest) {
        return franchiseService.updateFranchise(franchiseId, updFranchiseRequest);
    }
}
