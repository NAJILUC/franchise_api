package com.nequi.franchise.franchise.responses.utils;


import com.nequi.franchise.franchise.entities.Franchise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicIdNameResponse {

    private Long id;
    private String name;

    public BasicIdNameResponse(Franchise franchise) {
        this.id = franchise.getId();
        this.name = franchise.getName();
    }

}
