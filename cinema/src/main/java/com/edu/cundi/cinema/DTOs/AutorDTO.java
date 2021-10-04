package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO extends RepresentationModel<AutorDTO> {
    @NotBlank(message = "el id no puede estar vacio.")
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private int edad;
    public AutorDTO(String id) {
        this.id = id;
    }

}
