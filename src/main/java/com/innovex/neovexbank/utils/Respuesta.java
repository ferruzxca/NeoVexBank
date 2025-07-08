package com.innovex.neovexbank.utils;

public class Respuesta {
    private String mensaje;
    private boolean ok;

    public Respuesta(String mensaje, boolean ok) {
        this.mensaje = mensaje;
        this.ok = ok;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}