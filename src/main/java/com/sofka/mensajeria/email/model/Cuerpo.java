package com.sofka.mensajeria.email.model;

public class Cuerpo {
    private String correo;
    private String contenido;
    private String asunto;

    public Cuerpo(String correo, String contenido, String asunto) {
        this.correo = correo;
        this.contenido = contenido;
        this.asunto = asunto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public String toString() {
        return "Cuerpo{" +
                "correo='" + correo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", asunto='" + asunto + '\'' +
                '}';
    }
}
