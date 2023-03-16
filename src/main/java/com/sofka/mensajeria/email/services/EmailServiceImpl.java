package com.sofka.mensajeria.email.services;


import com.sofka.mensajeria.email.model.Cuerpo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Value("spring.mail.username")
    private String remitente;

    @Override
    public boolean enviarCorreo(Cuerpo cuerpo)  {
        LOGGER.info("EmailBody: {}", cuerpo.toString());
        boolean send = false;
        try {


            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom(remitente);
            mail.setTo(cuerpo.getCorreo());
            mail.setSubject(cuerpo.getAsunto());
            mail.setText(cuerpo.getContenido());

            sender.send(mail);
            LOGGER.info("Correo enviado!");
            send = true;
        } catch (Exception e) {
            LOGGER.error("Hubo un error al enviar el mail: {}", e);
        }

        return send;
    }

}
