package com.nequi.franchise.franchise.controllers;

import com.nequi.franchise.franchise.services.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;
}
