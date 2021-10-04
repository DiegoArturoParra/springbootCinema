package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class AutorIdDTO extends RepresentationModel<AutorDTO> {
    @NotBlank(message = "el id no puede estar vacio.")
    private String id;
}