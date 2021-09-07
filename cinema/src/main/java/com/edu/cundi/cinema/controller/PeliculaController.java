package com.edu.cundi.cinema.controller;

import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/peliculas/")
@Validated
public class PeliculaController {

    @Autowired
    @Qualifier("PeliculaService")
    private ICRUD<Pelicula> service;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaDTO> getPelicula(@PathVariable @Min(1) @NotNull int Id) {

        return ResponseEntity.ok(service.getById(Id));
    }

    @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaDTO> getPeliculas() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> CrearPelicula(@Valid @RequestBody Pelicula entity) {
        service.create(entity);
        URI location = URI.create(String.format("/peliculas/%d", entity.getId()));
        return ResponseEntity.created(location).build();
    }

}
