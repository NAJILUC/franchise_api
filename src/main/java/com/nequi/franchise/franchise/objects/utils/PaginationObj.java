package com.nequi.franchise.franchise.objects.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationObj {

    private int page;
    private int size;
    private String column;
    private String order;

}
