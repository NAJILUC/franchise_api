package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.requests.FranchiseRequest;
import com.nequi.franchise.franchise.requests.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BasicIdNameResponse createFranchise(@Valid @RequestBody FranchiseRequest franchiseRequest){
        return franchiseService.createFranchise(franchiseRequest);
    }

    @PostMapping(path = "/{franchiseId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StoreResponse createStore(@PathVariable Long franchiseId, @Valid @RequestBody StoreRequest storeRequest) {
        return franchiseService.createStore(franchiseId, storeRequest);
    }
}
