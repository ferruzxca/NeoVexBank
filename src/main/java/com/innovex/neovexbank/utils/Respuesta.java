package com.innovex.neovexbank.utils;

public class Respuesta {

    private String mensaje;
    private boolean estado;

    public Respuesta() {
    }

    public Respuesta(String mensaje, boolean estado) {
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}