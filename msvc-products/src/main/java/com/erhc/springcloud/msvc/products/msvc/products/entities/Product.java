package com.erhc.springcloud.msvc.products.msvc.products.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="productos")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -8028633793067800480L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;
}
