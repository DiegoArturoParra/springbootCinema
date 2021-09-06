package com.edu.cundi.cinema.controller;

import org.springframework.web.bind.annotation.RestController;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Pelicula;
import com.edu.cundi.cinema.services.interfaces.ICRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/pelicula/")
public class PeliculaController {

    @Autowired
    @Qualifier("PeliculaService")
    private ICRUD<Pelicula> service;

    @GetMapping(value = "listado",produces = "application/json")
    public ResponseEntity<RespuestaDTO> getPeliculas() {
        return ResponseEntity.ok(service.getAll());
    }

}
