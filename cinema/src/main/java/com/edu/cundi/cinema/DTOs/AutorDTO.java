package com.edu.cundi.cinema.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AutorDTO extends RepresentationModel<AutorDTO> {
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private int edad;
    public AutorDTO(String id) {
        this.id = id;
    }
    
}
