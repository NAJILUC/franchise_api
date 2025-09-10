package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
}
