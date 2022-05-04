package com.example.demo.Util;

import java.util.ArrayList;
import com.example.demo.models.Usuario;

public class Respuesta {

    private boolean estado;
    private String mensaje;
    private String detalle;
    private ArrayList<Usuario> data;

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

    public ArrayList<Usuario> getData() {
        return this.data;
    }

    public void setData(ArrayList<Usuario> data) {
        this.data = data;
    }

}
