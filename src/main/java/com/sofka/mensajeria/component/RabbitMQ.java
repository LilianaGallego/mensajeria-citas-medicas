package com.sofka.mensajeria.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.mensajeria.data.Body;
import com.sofka.mensajeria.data.Notification;
import com.sofka.mensajeria.email.model.Cuerpo;
import com.sofka.mensajeria.email.services.EmailService;

import com.sofka.mensajeria.serializer.JSONMapper;
import com.sofka.mensajeria.serializer.JSONMapperImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class RabbitMQ {
    public static final String EVENTS_QUEUE = "events.queue";
    private final JSONMapper mapper = new JSONMapperImpl();
    private final Logger logger = Logger.getLogger("RabbitMQ");
    @Autowired
    private EmailService emailService;


    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws JsonProcessingException {
        Notification notification = Notification.from(message);
        System.out.println(notification.getType());

        if(notification.getType()
                .equals("co.com.sofka.model.paciente.events.cita.CitaAgendada")){
            Body body = new Body();
            body = (Body) mapper.readFromJson(notification.getBody(), Body.class);


            System.out.println(mapper.readFromJson(notification.getBody(), Body.class));
            System.out.println(body.getFecha());
            String contenido = "Buen día, se confirma la cita medica con el Doctor Ramiro Fernandez" +
                    " para el día "
                    +body.getFecha() +" a las  "+body.getHora()+
                    "horas, por favor llegar 15 minutos antes para realizar el respectivo registro.\n\n" +
                    "Espero tenga un excelente día.";
            Cuerpo cuerpo = new Cuerpo(
                    body.getCorreo(),
                    contenido,
                    "Confirmación cita médica con el Doctor Ramiro Fernandez"
            );
            emailService.enviarCorreo(cuerpo);
            logger.info(notification.getBody().toString());

        }else{
            logger.info("1: " + "we currently don't have a listener for that event " +notification.toString());
        }
    }
}
