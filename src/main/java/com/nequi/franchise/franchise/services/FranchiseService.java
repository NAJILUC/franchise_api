package com.nequi.franchise.franchise.services;

import com.nequi.franchise.franchise.repositories.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
}
