package com.example.AngularTest.dto;

public class Mensaje {
    private String mensaje;

    public Mensaje(String msj) {
        this.mensaje = msj;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
