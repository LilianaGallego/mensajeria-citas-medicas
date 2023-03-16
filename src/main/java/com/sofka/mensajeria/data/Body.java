package com.sofka.mensajeria.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Data
@Getter
@Setter
@AllArgsConstructor
public class Body {
    public Instant when;
    public UUID uuid;
    public String type;
    private String aggregateRootId;
    private String aggregate;

    private Long versionType;

    private String id;
    private String correo;
    private String fecha;
    private String hora;
    private String estado;
    private String aggregateName;


    public Body() {
    }
}
