package com.nequi.franchise.franchise.entities.products;

import com.nequi.franchise.franchise.entities.stores.Store;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "products",
        indexes = {
                @Index(name = "idx_store_id", columnList = "store_id")
        })
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stock")
    private Long stock;

    @ManyToOne(optional = false, targetEntity = Store.class)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}