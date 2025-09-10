package com.nequi.franchise.franchise.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "stores",
        indexes = {
                @Index(name = "idx_franchise_id", columnList = "franchise_id")
        })
@Entity
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, targetEntity = Franchise.class)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;
}