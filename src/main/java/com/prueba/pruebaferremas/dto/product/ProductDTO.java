package com.prueba.pruebaferremas.dto.product;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String codigo;
    private String clasificacion;
    private short habilitado;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String nombre, BigDecimal precio, String codigo, String clasificacion, short habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.clasificacion = clasificacion;
        this.habilitado = habilitado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public short getHabilitado() {
        return habilitado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setHabilitado(short habilitado) {
        this.habilitado = habilitado;
    }
}
