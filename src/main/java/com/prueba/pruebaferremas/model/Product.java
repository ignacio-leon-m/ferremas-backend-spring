package com.prueba.pruebaferremas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity // Lombok annotation para indicar que es una entidad JPA
@Data // Lombok annotation para generar getters, setters, equals, hashCode, toString
public class Product {
    @Id // Lombok annotation para indicar la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lombok annotation para generar el ID automáticamente
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false, length = 20, unique = true)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String clasificacion;

    @Column(nullable = false, length = 1)
    private short habilitado;

    public Product(String s, BigDecimal bigDecimal, String s1, String electrodoméstico) {
    }

    public Product() {

    }
}

