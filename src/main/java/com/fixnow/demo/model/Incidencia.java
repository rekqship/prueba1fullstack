package com.fixnow.demo.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Incidencia {
    private String id;
    private String descripcion;
    private String estado; 
    private String nivelPrioridad; 
    private String usuarioReporta;
    private LocalDate fechaRegistro;

    public Incidencia() {
        this.id = UUID.randomUUID().toString();
        this.fechaRegistro = LocalDate.now();
    }
}
