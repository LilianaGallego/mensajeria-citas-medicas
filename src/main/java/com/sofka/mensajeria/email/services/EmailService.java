package com.sofka.mensajeria.email.services;

import com.sofka.mensajeria.email.model.Cuerpo;

public interface EmailService {

    public boolean enviarCorreo(Cuerpo cuerpo);

}
