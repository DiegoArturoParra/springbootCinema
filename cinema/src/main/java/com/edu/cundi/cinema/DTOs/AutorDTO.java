package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {
    @NotBlank(message = "el id no puede estar vacio.")
    private String id;
}
