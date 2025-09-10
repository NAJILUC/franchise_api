package com.nequi.franchise.franchise.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "franchises")
@Entity
@Data
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}