package com.edu.cundi.cinema.DTOs;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDTO extends RepresentationModel<PeliculaDTO>  {

    private String id;
    private String nombre;
    private AutorIdDTO autor;
    private LocalDate fechaDeSalida;
    private String reseñaDePelicula;

}
