package com.example.demo.Util;

public class EntityReporte {
    private Integer id_producto;
    private Integer catidad_vendida;
    private Integer total_vendido;
    private String nombre_producto;

    public Integer getId_producto() {
        return this.id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCatidad_vendida() {
        return this.catidad_vendida;
    }

    public void setCatidad_vendida(Integer catidad_vendida) {
        this.catidad_vendida = catidad_vendida;
    }

    public Integer getTotal_vendido() {
        return this.total_vendido;
    }

    public void setTotal_vendido(Integer total_vendido) {
        this.total_vendido = total_vendido;
    }

    public String getNombre_producto() {
        return this.nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

}
