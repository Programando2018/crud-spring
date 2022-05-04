package com.example.demo.Util;

import java.util.List;

public class RespuestaInforme {

    private boolean estado;
    private String mensaje;
    private String detalle;
    private List<RespuestaReporteFiltros> data;

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<RespuestaReporteFiltros> getData() {
        return this.data;
    }

    public void setData(List<RespuestaReporteFiltros> data) {
        this.data = data;
    }

}
