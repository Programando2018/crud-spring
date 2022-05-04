package com.example.demo.Util;

import java.util.ArrayList;
import com.example.demo.models.Producto;

public class RespuestProductos {
    private boolean estado;
    private String mensaje;
    private String detalle;
    private ArrayList<Producto> data;

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

    public ArrayList<Producto> getData() {
        return this.data;
    }

    public void setData(ArrayList<Producto> data) {
        this.data = data;
    }

}
