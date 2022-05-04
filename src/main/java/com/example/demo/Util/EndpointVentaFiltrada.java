package com.example.demo.Util;

public class EndpointVentaFiltrada {
    private String fechaInicial;
    private String fechaFinal;
    private String nombreProducto;
    private Integer precioInicial;
    private Integer precioFinal;

    public String getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getPrecioInicial() {
        return this.precioInicial;
    }

    public void setPrecioInicial(Integer precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Integer getPrecioFinal() {
        return this.precioFinal;
    }

    public void setPrecioFina(Integer precioTope) {
        this.precioFinal = precioTope;
    }

}
