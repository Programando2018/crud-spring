package com.example.demo.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Integer cantidad;
    private Date fecha_venta;
    private String usuario_venta;
    private long id_producto;
    private Integer total;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario_venta() {
        return this.usuario_venta;
    }

    public void setUsuario_venta(String usuario_venta) {
        this.usuario_venta = usuario_venta;
    }

    public long getId_producto() {
        return this.id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getFecha_venta() {
        return this.fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

}